package com.incture.erasm.dto.response;

import java.util.List;

public class EmployeeResponseDto {

    private Long employeeId;

    private String employeeCode;

    private String department;

    private String designation;

    private int experience;

    private boolean availability;

    private UserResponseDto user;

    private RoleResponseDto role;

    private List<SkillResponseDto> skills;

    public EmployeeResponseDto() {
    }

    public EmployeeResponseDto(Long employeeId,
            String employeeCode,
            String department,
            String designation,
            int experience,
            boolean availability,
            UserResponseDto user,
            RoleResponseDto role,
            List<SkillResponseDto> skills) {

        this.employeeId = employeeId;
        this.employeeCode = employeeCode;
        this.department = department;
        this.designation = designation;
        this.experience = experience;
        this.availability = availability;
        this.user = user;
        this.role = role;
        this.skills = skills;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public UserResponseDto getUser() {
        return user;
    }

    public void setUser(UserResponseDto user) {
        this.user = user;
    }

    public RoleResponseDto getRole() {
        return role;
    }

    public void setRole(RoleResponseDto role) {
        this.role = role;
    }

    public List<SkillResponseDto> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillResponseDto> skills) {
        this.skills = skills;
    }
}