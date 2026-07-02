package com.incture.erasm.dto.response;

import java.util.List;

public class ProjectResponseDto {

    private Long projectId;
    private String projectName;
    private String clientName;
    private String startDate;
    private String endDate;
    private String technologyStack;
    private Double budget;

    private List<ResourceRequestResponseDto> resourceRequests;

    public ProjectResponseDto() {}

    public ProjectResponseDto(Long projectId, String projectName,
            String clientName, String startDate,
            String endDate, String technologyStack,
            Double budget,
            List<ResourceRequestResponseDto> resourceRequests) {

        this.projectId = projectId;
        this.projectName = projectName;
        this.clientName = clientName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.technologyStack = technologyStack;
        this.budget = budget;
        this.resourceRequests = resourceRequests;
    }

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getTechnologyStack() {
		return technologyStack;
	}

	public void setTechnologyStack(String technologyStack) {
		this.technologyStack = technologyStack;
	}

	public Double getBudget() {
		return budget;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
	}

	public List<ResourceRequestResponseDto> getResourceRequests() {
		return resourceRequests;
	}

	public void setResourceRequests(List<ResourceRequestResponseDto> resourceRequests) {
		this.resourceRequests = resourceRequests;
	}


}