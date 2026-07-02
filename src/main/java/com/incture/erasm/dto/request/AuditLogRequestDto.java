package com.incture.erasm.dto.request;

import jakarta.validation.constraints.NotBlank;

public class AuditLogRequestDto {

    @NotBlank
    private String action;

    @NotBlank
    private String createdBy;

    @NotBlank
    private String modifiedBy;

    @NotBlank
    private String createdDate;

    @NotBlank
    private String modifiedDate;

    public AuditLogRequestDto() {}

    public AuditLogRequestDto(String action,
            String createdBy,
            String modifiedBy,
            String createdDate,
            String modifiedDate) {

        this.action = action;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}