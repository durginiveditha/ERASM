package com.incture.erasm.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ResourceRequestRequestDto {

    @NotBlank
    private String requiredSkill;

    @NotNull
    private Integer requiredCount;

    @NotBlank
    private String status;

    @NotNull
    private Long projectId;

    public ResourceRequestRequestDto() {}

    public ResourceRequestRequestDto(String requiredSkill,
            Integer requiredCount,
            String status,
            Long projectId) {

        this.requiredSkill = requiredSkill;
        this.requiredCount = requiredCount;
        this.status = status;
        this.projectId = projectId;
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

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

 
}