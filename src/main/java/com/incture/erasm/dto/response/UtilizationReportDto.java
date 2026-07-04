package com.incture.erasm.dto.response;

public class UtilizationReportDto {

    private String employeeCode;
    private Double utilization;

    public UtilizationReportDto() {
    }

    public UtilizationReportDto(String employeeCode,
                                Double utilization) {
        this.employeeCode = employeeCode;
        this.utilization = utilization;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public Double getUtilization() {
        return utilization;
    }

    public void setUtilization(Double utilization) {
        this.utilization = utilization;
    }
}