package com.incture.erasm.dto.response;

public class AllocationReportDto {

    private String employeeCode;
    private String projectName;
    private Double allocationPercentage;

    public AllocationReportDto() {
    }

    public AllocationReportDto(String employeeCode,
                               String projectName,
                               Double allocationPercentage) {
        this.employeeCode = employeeCode;
        this.projectName = projectName;
        this.allocationPercentage = allocationPercentage;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Double getAllocationPercentage() {
        return allocationPercentage;
    }

    public void setAllocationPercentage(Double allocationPercentage) {
        this.allocationPercentage = allocationPercentage;
    }
}