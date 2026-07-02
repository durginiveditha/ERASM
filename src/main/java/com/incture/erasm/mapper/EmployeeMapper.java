package com.incture.erasm.mapper;

import com.incture.erasm.dto.response.EmployeeResponseDto;
import com.incture.erasm.entity.Employee;

public class EmployeeMapper {

    private EmployeeMapper() {
    }

    // Entity -> Response DTO
    public static EmployeeResponseDto entityToResponseDto(Employee employee) {

        EmployeeResponseDto responseDto = new EmployeeResponseDto();

        responseDto.setEmployeeId(employee.getEmployeeId());
        responseDto.setEmployeeCode(employee.getEmployeeCode());
        responseDto.setDepartment(employee.getDepartment());
        responseDto.setDesignation(employee.getDesignation());
        responseDto.setExperience(employee.getExperience());
        responseDto.setAvailability(employee.isAvailability());

        responseDto.setUser(
                UserMapper.entityToResponseDto(employee.getUser()));

        responseDto.setRole(
                RoleMapper.entityToResponseDto(employee.getRole()));

        return responseDto;
    }

    // Update existing Employee
    public static void updateEntity(Employee employee,
                                    String employeeCode,
                                    String department,
                                    String designation,
                                    int experience,
                                    boolean availability) {

        employee.setEmployeeCode(employeeCode);
        employee.setDepartment(department);
        employee.setDesignation(designation);
        employee.setExperience(experience);
        employee.setAvailability(availability);
    }
}