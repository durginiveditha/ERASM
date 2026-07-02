package com.incture.erasm.dto.request;

import jakarta.validation.constraints.NotNull;

public class AllocationRequestDto {

    @NotNull
    private Long employeeId;

    @NotNull
    private Long projectId;

    @NotNull
    private Double allocationPercentage;

    private String allocationDate;

    private String releaseDate;

    private String status;

    public AllocationRequestDto() {}

    public AllocationRequestDto(Long employeeId,
            Long projectId,
            Double allocationPercentage,
            String allocationDate,
            String releaseDate,
            String status) {

        this.employeeId = employeeId;
        this.projectId = projectId;
        this.allocationPercentage = allocationPercentage;
        this.allocationDate = allocationDate;
        this.releaseDate = releaseDate;
        this.status = status;
    }

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Double getAllocationPercentage() {
		return allocationPercentage;
	}

	public void setAllocationPercentage(Double allocationPercentage) {
		this.allocationPercentage = allocationPercentage;
	}

	public String getAllocationDate() {
		return allocationDate;
	}

	public void setAllocationDate(String allocationDate) {
		this.allocationDate = allocationDate;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}