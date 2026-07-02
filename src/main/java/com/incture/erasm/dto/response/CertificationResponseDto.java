package com.incture.erasm.dto.response;

public class CertificationResponseDto {

    private Long certificationId;
    private String certificationName;
    private String issuingOrganization;
    private String issueDate;
    private String expiryDate;
    private EmployeeResponseDto employee;

    public CertificationResponseDto() {
    }

    public CertificationResponseDto(Long certificationId,
            String certificationName,
            String issuingOrganization,
            String issueDate,
            String expiryDate,
            EmployeeResponseDto employee) {

        this.certificationId = certificationId;
        this.certificationName = certificationName;
        this.issuingOrganization = issuingOrganization;
        this.issueDate = issueDate;
        this.expiryDate = expiryDate;
        this.employee = employee;
    }

    public Long getCertificationId() {
        return certificationId;
    }

    public void setCertificationId(Long certificationId) {
        this.certificationId = certificationId;
    }

    public String getCertificationName() {
        return certificationName;
    }

    public void setCertificationName(String certificationName) {
        this.certificationName = certificationName;
    }

    public String getIssuingOrganization() {
        return issuingOrganization;
    }

    public void setIssuingOrganization(String issuingOrganization) {
        this.issuingOrganization = issuingOrganization;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public EmployeeResponseDto getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeResponseDto employee) {
        this.employee = employee;
    }
}