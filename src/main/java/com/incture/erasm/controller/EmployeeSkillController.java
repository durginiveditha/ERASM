package com.incture.erasm.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.incture.erasm.dto.request.EmployeeSkillRequestDto;
import com.incture.erasm.dto.response.EmployeeSkillResponseDto;
import com.incture.erasm.service.EmployeeSkillService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employee-skills")
public class EmployeeSkillController {

    private final EmployeeSkillService employeeSkillService;

    public EmployeeSkillController(EmployeeSkillService employeeSkillService) {
        this.employeeSkillService = employeeSkillService;
    }

    // Create Employee Skill
    @PostMapping
    public ResponseEntity<EmployeeSkillResponseDto> createEmployeeSkill(
            @Valid @RequestBody EmployeeSkillRequestDto requestDto) {

        EmployeeSkillResponseDto response =
                employeeSkillService.createEmployeeSkill(requestDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get Employee Skill By ID
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeSkillResponseDto> getEmployeeSkillById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                employeeSkillService.getEmployeeSkillById(id));
    }

    // Get All Employee Skills
    @GetMapping
    public ResponseEntity<List<EmployeeSkillResponseDto>> getAllEmployeeSkills() {

        return ResponseEntity.ok(
                employeeSkillService.getAllEmployeeSkills());
    }

    // Update Employee Skill
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeSkillResponseDto> updateEmployeeSkill(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeSkillRequestDto requestDto) {

        return ResponseEntity.ok(
                employeeSkillService.updateEmployeeSkill(id, requestDto));
    }

    // Delete Employee Skill
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployeeSkill(
            @PathVariable Long id) {

        employeeSkillService.deleteEmployeeSkill(id);

        return ResponseEntity.ok("Employee Skill deleted successfully.");
    }
}