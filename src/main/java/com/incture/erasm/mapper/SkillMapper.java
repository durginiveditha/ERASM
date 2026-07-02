package com.incture.erasm.mapper;

import com.incture.erasm.dto.request.SkillRequestDto;
import com.incture.erasm.dto.response.SkillResponseDto;
import com.incture.erasm.entity.Skill;

public class SkillMapper {

    private SkillMapper() {
    }

    // Request DTO -> Entity
    public static Skill requestDtoToEntity(SkillRequestDto requestDto) {

        Skill skill = new Skill();

        skill.setSkillName(requestDto.getSkillName());
        skill.setDescription(requestDto.getDescription());

        return skill;
    }

    // Entity -> Response DTO
    public static SkillResponseDto entityToResponseDto(Skill skill) {

        SkillResponseDto responseDto = new SkillResponseDto();

        responseDto.setSkillId(skill.getSkillId());
        responseDto.setSkillName(skill.getSkillName());
        responseDto.setDescription(skill.getDescription());

        return responseDto;
    }

    // Update Entity
    public static void updateEntityFromRequestDto(
            SkillRequestDto requestDto,
            Skill skill) {

        skill.setSkillName(requestDto.getSkillName());
        skill.setDescription(requestDto.getDescription());
    }
}