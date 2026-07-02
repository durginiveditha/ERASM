package com.incture.erasm.mapper;

import com.incture.erasm.dto.request.EmployeeSkillRequestDto;
import com.incture.erasm.dto.response.EmployeeSkillResponseDto;
import com.incture.erasm.entity.EmployeeSkill;

public class EmployeeSkillMapper {

    private EmployeeSkillMapper() {
    }

    // Entity -> Response DTO
    public static EmployeeSkillResponseDto entityToResponseDto(EmployeeSkill employeeSkill) {

        EmployeeSkillResponseDto responseDto = new EmployeeSkillResponseDto();

        responseDto.setEmployeeSkillId(employeeSkill.getEmployeeSkillId());
        responseDto.setSkillLevel(employeeSkill.getSkillLevel());
        responseDto.setExperienceYears(employeeSkill.getExperienceYears());

        responseDto.setEmployee(
                EmployeeMapper.entityToResponseDto(employeeSkill.getEmployee()));

        responseDto.setSkill(
                SkillMapper.entityToResponseDto(employeeSkill.getSkill()));

        return responseDto;
    }

    // Update existing EmployeeSkill
    public static void updateEntityFromRequestDto(
            EmployeeSkillRequestDto requestDto,
            EmployeeSkill employeeSkill) {

        employeeSkill.setSkillLevel(requestDto.getSkillLevel());
        employeeSkill.setExperienceYears(requestDto.getExperienceYears());
    }
}