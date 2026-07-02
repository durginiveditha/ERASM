package com.incture.erasm.dto.response;

public class AuditLogResponseDto {

    private Long auditId;

    private String action;

    private String createdBy;

    private String modifiedBy;

    private String createdDate;

    private String modifiedDate;

    public AuditLogResponseDto() {}

    public AuditLogResponseDto(Long auditId,
            String action,
            String createdBy,
            String modifiedBy,
            String createdDate,
            String modifiedDate) {

        this.auditId = auditId;
        this.action = action;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

	public Long getAuditId() {
		return auditId;
	}

	public void setAuditId(Long auditId) {
		this.auditId = auditId;
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