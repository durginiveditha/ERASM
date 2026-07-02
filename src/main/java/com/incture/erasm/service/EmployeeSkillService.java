package com.incture.erasm.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.incture.erasm.dto.request.EmployeeSkillRequestDto;
import com.incture.erasm.dto.response.EmployeeSkillResponseDto;
import com.incture.erasm.entity.Employee;
import com.incture.erasm.entity.EmployeeSkill;
import com.incture.erasm.entity.Skill;
import com.incture.erasm.mapper.EmployeeSkillMapper;
import com.incture.erasm.repository.EmployeeRepository;
import com.incture.erasm.repository.EmployeeSkillRepository;
import com.incture.erasm.repository.SkillRepository;

import com.incture.erasm.exception.ResourceNotFoundException;

@Service
public class EmployeeSkillService {

    private final EmployeeSkillRepository employeeSkillRepository;
    private final EmployeeRepository employeeRepository;
    private final SkillRepository skillRepository;

    public EmployeeSkillService(EmployeeSkillRepository employeeSkillRepository,
                                EmployeeRepository employeeRepository,
                                SkillRepository skillRepository) {
        this.employeeSkillRepository = employeeSkillRepository;
        this.employeeRepository = employeeRepository;
        this.skillRepository = skillRepository;
    }

    // Create Employee Skill
    public EmployeeSkillResponseDto createEmployeeSkill(EmployeeSkillRequestDto requestDto) {

        Employee employee = employeeRepository.findById(requestDto.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Skill skill = skillRepository.findById(requestDto.getSkillId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        EmployeeSkill employeeSkill = new EmployeeSkill();

        employeeSkill.setEmployee(employee);
        employeeSkill.setSkill(skill);
        employeeSkill.setSkillLevel(requestDto.getSkillLevel());
        employeeSkill.setExperienceYears(requestDto.getExperienceYears());

        EmployeeSkill savedEmployeeSkill = employeeSkillRepository.save(employeeSkill);

        return EmployeeSkillMapper.entityToResponseDto(savedEmployeeSkill);
    }

    // Get Employee Skill By Id
    public EmployeeSkillResponseDto getEmployeeSkillById(Long employeeSkillId) {

        EmployeeSkill employeeSkill = employeeSkillRepository.findById(employeeSkillId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return EmployeeSkillMapper.entityToResponseDto(employeeSkill);
    }

    // Get All Employee Skills
    public List<EmployeeSkillResponseDto> getAllEmployeeSkills() {

        return employeeSkillRepository.findAll()
                .stream()
                .map(EmployeeSkillMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    // Update Employee Skill
    public EmployeeSkillResponseDto updateEmployeeSkill(Long employeeSkillId,
                                                        EmployeeSkillRequestDto requestDto) {

        EmployeeSkill employeeSkill = employeeSkillRepository.findById(employeeSkillId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Employee employee = employeeRepository.findById(requestDto.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Skill skill = skillRepository.findById(requestDto.getSkillId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        EmployeeSkillMapper.updateEntityFromRequestDto(requestDto, employeeSkill);

        employeeSkill.setEmployee(employee);
        employeeSkill.setSkill(skill);

        EmployeeSkill updatedEmployeeSkill = employeeSkillRepository.save(employeeSkill);

        return EmployeeSkillMapper.entityToResponseDto(updatedEmployeeSkill);
    }

    // Delete Employee Skill
    public void deleteEmployeeSkill(Long employeeSkillId) {

        EmployeeSkill employeeSkill = employeeSkillRepository.findById(employeeSkillId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        employeeSkillRepository.delete(employeeSkill);
    }
}