package com.incture.erasm.dto.response;

public class EmployeeSkillResponseDto {

    private Long employeeSkillId;

    private EmployeeResponseDto employee;

    private SkillResponseDto skill;

    private String skillLevel;

    private int experienceYears;

    public EmployeeSkillResponseDto() {
    }

    public EmployeeSkillResponseDto(Long employeeSkillId,
                                    EmployeeResponseDto employee,
                                    SkillResponseDto skill,
                                    String skillLevel,
                                    int experienceYears) {
        this.employeeSkillId = employeeSkillId;
        this.employee = employee;
        this.skill = skill;
        this.skillLevel = skillLevel;
        this.experienceYears = experienceYears;
    }

    public Long getEmployeeSkillId() {
        return employeeSkillId;
    }

    public void setEmployeeSkillId(Long employeeSkillId) {
        this.employeeSkillId = employeeSkillId;
    }

    public EmployeeResponseDto getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeResponseDto employee) {
        this.employee = employee;
    }

    public SkillResponseDto getSkill() {
        return skill;
    }

    public void setSkill(SkillResponseDto skill) {
        this.skill = skill;
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