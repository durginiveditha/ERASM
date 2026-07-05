package com.incture.erasm.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.incture.erasm.dto.request.AllocationRequestDto;
import com.incture.erasm.dto.response.AllocationResponseDto;
import com.incture.erasm.entity.Allocation;
import com.incture.erasm.entity.Employee;
import com.incture.erasm.entity.Project;
import com.incture.erasm.mapper.AllocationMapper;
import com.incture.erasm.repository.AllocationRepository;
import com.incture.erasm.repository.EmployeeRepository;
import com.incture.erasm.repository.ProjectRepository;
import com.incture.erasm.exception.AllocationException;
import com.incture.erasm.exception.ProjectNotFoundException;
import com.incture.erasm.exception.ResourceNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AllocationService {

    private static final Logger logger = LoggerFactory.getLogger(AllocationService.class);
    private final AllocationRepository allocationRepository;
    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;

    public AllocationService(AllocationRepository allocationRepository,
                             EmployeeRepository employeeRepository,
                             ProjectRepository projectRepository) {
        this.allocationRepository = allocationRepository;
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
    }

    // Create Allocation
    public AllocationResponseDto createAllocation(AllocationRequestDto requestDto) {

        Employee employee = employeeRepository.findById(requestDto.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + requestDto.getEmployeeId()));

        Project project = projectRepository.findById(requestDto.getProjectId())
                .orElseThrow(() -> new ProjectNotFoundException("Project not found with ID: " + requestDto.getProjectId()));

        List<Allocation> existingAllocations =
                allocationRepository.findByEmployee(employee);

        double totalAllocation = existingAllocations.stream()
                .mapToDouble(Allocation::getAllocationPercentage)
                .sum();

        totalAllocation += requestDto.getAllocationPercentage();

        if (totalAllocation > 100) {
            throw new AllocationException("Employee allocation cannot exceed 100%");
        }

        logger.info("Allocating employee {} to project {}",
                employee.getEmployeeId(),
                project.getProjectId());

        Allocation allocation = new Allocation();
        allocation.setEmployee(employee);
        allocation.setProject(project);
        allocation.setAllocationPercentage(requestDto.getAllocationPercentage());
        allocation.setAllocationDate(requestDto.getAllocationDate());
        allocation.setReleaseDate(requestDto.getReleaseDate());
        allocation.setStatus(requestDto.getStatus());

        Allocation savedAllocation = allocationRepository.save(allocation);
        logger.info("Allocation created successfully.");

        return AllocationMapper.entityToResponseDto(savedAllocation);
    }

    // Get Allocation By Id
    public AllocationResponseDto getAllocationById(Long allocationId) {

        logger.info("Fetching allocation with ID: {}", allocationId);
        Allocation allocation = allocationRepository.findById(allocationId)
                .orElseThrow(() -> {
                    logger.warn("Allocation not found with ID: {}", allocationId);
                    return new AllocationException("Allocation not found with ID: " + allocationId);
                });

        return AllocationMapper.entityToResponseDto(allocation);
    }

    // Get All Allocations
    public List<AllocationResponseDto> getAllAllocations() {

        logger.info("Fetching all allocations");
        return allocationRepository.findAll()
                .stream()
                .map(AllocationMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    // Update Allocation
    public AllocationResponseDto updateAllocation(Long allocationId,
                                                  AllocationRequestDto requestDto) {

        logger.info("Updating allocation with ID: {}", allocationId);
        Allocation allocation = allocationRepository.findById(allocationId)
                .orElseThrow(() -> {
                    logger.warn("Allocation not found with ID: {}", allocationId);
                    return new AllocationException("Allocation not found with ID: " + allocationId);
                });

        Employee employee = employeeRepository.findById(requestDto.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + requestDto.getEmployeeId()));

        Project project = projectRepository.findById(requestDto.getProjectId())
                .orElseThrow(() -> new ProjectNotFoundException("Project not found with ID: " + requestDto.getProjectId()));

        AllocationMapper.updateEntityFromRequestDto(requestDto, allocation);
        allocation.setEmployee(employee);
        allocation.setProject(project);

        Allocation updatedAllocation = allocationRepository.save(allocation);
        logger.info("Allocation updated successfully. ID: {}", allocationId);

        return AllocationMapper.entityToResponseDto(updatedAllocation);
    }

    // Delete Allocation
    public void deleteAllocation(Long allocationId) {

        logger.info("Deleting allocation with ID: {}", allocationId);
        Allocation allocation = allocationRepository.findById(allocationId)
                .orElseThrow(() -> {
                    logger.warn("Allocation not found with ID: {}", allocationId);
                    return new AllocationException("Allocation not found with ID: " + allocationId);
                });

        allocationRepository.delete(allocation);
        logger.info("Allocation deleted successfully. ID: {}", allocationId);
    }
}