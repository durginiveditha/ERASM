package com.incture.erasm.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "allocations")
public class Allocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long allocationId;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Column(nullable = false)
    private Double allocationPercentage;

    @Column(nullable = false)
    private String allocationDate;

    private String releaseDate;

    @Column(nullable = false)
    private String status;

    // Default Constructor
    public Allocation() {
    }

    // Parameterized Constructor
    public Allocation(Long allocationId, Employee employee, Project project,
                      Double allocationPercentage, String allocationDate,
                      String releaseDate, String status) {
        this.allocationId = allocationId;
        this.employee = employee;
        this.project = project;
        this.allocationPercentage = allocationPercentage;
        this.allocationDate = allocationDate;
        this.releaseDate = releaseDate;
        this.status = status;
    }

    // Getters and Setters

    public Long getAllocationId() {
        return allocationId;
    }

    public void setAllocationId(Long allocationId) {
        this.allocationId = allocationId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
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