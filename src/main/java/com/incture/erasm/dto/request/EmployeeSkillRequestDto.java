package com.incture.erasm.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class EmployeeSkillRequestDto {

    @NotNull(message = "Employee ID is required")
    private Long employeeId;

    @NotNull(message = "Skill ID is required")
    private Long skillId;

    @NotBlank(message = "Skill level is required")
    private String skillLevel;

    @PositiveOrZero(message = "Experience cannot be negative")
    private int experienceYears;

    public EmployeeSkillRequestDto() {
    }

    public EmployeeSkillRequestDto(Long employeeId, Long skillId,
                                   String skillLevel, int experienceYears) {
        this.employeeId = employeeId;
        this.skillId = skillId;
        this.skillLevel = skillLevel;
        this.experienceYears = experienceYears;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public String getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }
}