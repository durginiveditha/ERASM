package com.incture.erasm.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "resource_requests")
public class ResourceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestId;

    @Column(nullable = false)
    private String requiredSkill;

    @Column(nullable = false)
    private int requiredCount;

    @Column(nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    // Default Constructor
    public ResourceRequest() {
    }

    // Parameterized Constructor
    public ResourceRequest(Long requestId, String requiredSkill,
            int requiredCount, String status, Project project) {
        this.requestId = requestId;
        this.requiredSkill = requiredSkill;
        this.requiredCount = requiredCount;
        this.status = status;
        this.project = project;
    }

    // Getters and Setters

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

    public int getRequiredCount() {
        return requiredCount;
    }

    public void setRequiredCount(int requiredCount) {
        this.requiredCount = requiredCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}