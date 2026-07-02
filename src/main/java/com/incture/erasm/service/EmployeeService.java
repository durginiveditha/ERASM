package com.incture.erasm.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.incture.erasm.dto.request.EmployeeRequestDto;
import com.incture.erasm.dto.response.EmployeeResponseDto;
import com.incture.erasm.entity.Employee;
import com.incture.erasm.entity.Role;
import com.incture.erasm.entity.User;
import com.incture.erasm.mapper.EmployeeMapper;
import com.incture.erasm.repository.EmployeeRepository;
import com.incture.erasm.repository.RoleRepository;
import com.incture.erasm.repository.UserRepository;

import com.incture.erasm.exception.ResourceNotFoundException;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public EmployeeService(EmployeeRepository employeeRepository,
                           UserRepository userRepository,
                           RoleRepository roleRepository) {

        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    // Create Employee
    public EmployeeResponseDto createEmployee(EmployeeRequestDto requestDto) {

        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Role role = roleRepository.findById(requestDto.getRoleId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Employee employee = new Employee();

        employee.setEmployeeCode(requestDto.getEmployeeCode());
        employee.setDepartment(requestDto.getDepartment());
        employee.setDesignation(requestDto.getDesignation());
        employee.setExperience(requestDto.getExperience());
        employee.setAvailability(requestDto.isAvailability());

        employee.setUser(user);
        employee.setRole(role);

        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.entityToResponseDto(savedEmployee);
    }

    // Get Employee By Id
    public EmployeeResponseDto getEmployeeById(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return EmployeeMapper.entityToResponseDto(employee);
    }

    // Get All Employees
    public List<EmployeeResponseDto> getAllEmployees() {

        return employeeRepository.findAll()
                .stream()
                .map(EmployeeMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    // Update Employee
    public EmployeeResponseDto updateEmployee(Long employeeId,
                                              EmployeeRequestDto requestDto) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Role role = roleRepository.findById(requestDto.getRoleId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        EmployeeMapper.updateEntity(
                employee,
                requestDto.getEmployeeCode(),
                requestDto.getDepartment(),
                requestDto.getDesignation(),
                requestDto.getExperience(),
                requestDto.isAvailability());

        employee.setUser(user);
        employee.setRole(role);

        Employee updatedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.entityToResponseDto(updatedEmployee);
    }

    // Delete Employee
    public void deleteEmployee(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        employeeRepository.delete(employee);
    }
}