package com.incture.erasm.dto.response;

public class AllocationResponseDto {

    private Long allocationId;

    private EmployeeResponseDto employee;

    private ProjectResponseDto project;

    private Double allocationPercentage;

    private String allocationDate;

    private String releaseDate;

    private String status;

    public AllocationResponseDto() {}

    public AllocationResponseDto(Long allocationId,
            EmployeeResponseDto employee,
            ProjectResponseDto project,
            Double allocationPercentage,
            String allocationDate,
            String releaseDate,
            String status) {

        this.allocationId = allocationId;
        this.employee = employee;
        this.project = project;
        this.allocationPercentage = allocationPercentage;
        this.allocationDate = allocationDate;
        this.releaseDate = releaseDate;
        this.status = status;
    }

	public Long getAllocationId() {
		return allocationId;
	}

	public void setAllocationId(Long allocationId) {
		this.allocationId = allocationId;
	}

	public EmployeeResponseDto getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeResponseDto employee) {
		this.employee = employee;
	}

	public ProjectResponseDto getProject() {
		return project;
	}

	public void setProject(ProjectResponseDto project) {
		this.project = project;
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