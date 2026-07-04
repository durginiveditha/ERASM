package com.incture.erasm.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.incture.erasm.dto.request.SkillRequestDto;
import com.incture.erasm.dto.response.SkillResponseDto;
import com.incture.erasm.entity.Skill;
import com.incture.erasm.mapper.SkillMapper;
import com.incture.erasm.repository.SkillRepository;

import com.incture.erasm.exception.SkillNotFoundException;

@Service
public class SkillService {

    private final SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    // Create Skill
    public SkillResponseDto createSkill(SkillRequestDto requestDto) {

        Skill skill = SkillMapper.requestDtoToEntity(requestDto);

        Skill savedSkill = skillRepository.save(skill);

        return SkillMapper.entityToResponseDto(savedSkill);
    }

    // Get Skill By Id
    public SkillResponseDto getSkillById(Long skillId) {

        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new SkillNotFoundException("Skill not found"));

        return SkillMapper.entityToResponseDto(skill);
    }

    // Get All Skills
    public List<SkillResponseDto> getAllSkills() {

        return skillRepository.findAll()
                .stream()
                .map(SkillMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    // Update Skill
    public SkillResponseDto updateSkill(Long skillId,
                                        SkillRequestDto requestDto) {

        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new SkillNotFoundException("Skill not found"));

        SkillMapper.updateEntityFromRequestDto(requestDto, skill);

        Skill updatedSkill = skillRepository.save(skill);

        return SkillMapper.entityToResponseDto(updatedSkill);
    }

    // Delete Skill
    public void deleteSkill(Long skillId) {

        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new SkillNotFoundException("Skill not found"));

        skillRepository.delete(skill);
    }
}