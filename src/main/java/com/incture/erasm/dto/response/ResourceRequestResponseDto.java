package com.incture.erasm.dto.response;

public class ResourceRequestResponseDto {

    private Long requestId;
    private String requiredSkill;
    private Integer requiredCount;
    private String status;

    private ProjectResponseDto project;

    public ResourceRequestResponseDto() {}

    public ResourceRequestResponseDto(Long requestId,
            String requiredSkill,
            Integer requiredCount,
            String status,
            ProjectResponseDto project) {

        this.requestId = requestId;
        this.requiredSkill = requiredSkill;
        this.requiredCount = requiredCount;
        this.status = status;
        this.project = project;
    }

	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	public String getRequiredSkill() {
		return requiredSkill;
	}

	public void setRequiredSkill(String requiredSkill) {
		this.requiredSkill = requiredSkill;
	}

	public Integer getRequiredCount() {
		return requiredCount;
	}

	public void setRequiredCount(Integer requiredCount) {
		this.requiredCount = requiredCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ProjectResponseDto getProject() {
		return project;
	}

	public void setProject(ProjectResponseDto project) {
		this.project = project;
	}

}