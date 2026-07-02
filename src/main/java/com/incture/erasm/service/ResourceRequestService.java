package com.incture.erasm.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.incture.erasm.dto.request.ResourceRequestRequestDto;
import com.incture.erasm.dto.response.ResourceRequestResponseDto;
import com.incture.erasm.entity.Project;
import com.incture.erasm.entity.ResourceRequest;
import com.incture.erasm.mapper.ResourceRequestMapper;
import com.incture.erasm.repository.ProjectRepository;
import com.incture.erasm.repository.ResourceRequestRepository;

import com.incture.erasm.exception.ResourceNotFoundException;

@Service
public class ResourceRequestService {

    private final ResourceRequestRepository resourceRequestRepository;
    private final ProjectRepository projectRepository;

    public ResourceRequestService(ResourceRequestRepository resourceRequestRepository,
                                  ProjectRepository projectRepository) {

        this.resourceRequestRepository = resourceRequestRepository;
        this.projectRepository = projectRepository;
    }

    // Create Resource Request
    public ResourceRequestResponseDto createResourceRequest(ResourceRequestRequestDto requestDto) {

        Project project = projectRepository.findById(requestDto.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        ResourceRequest request = new ResourceRequest();

        request.setRequiredSkill(requestDto.getRequiredSkill());
        request.setRequiredCount(requestDto.getRequiredCount());
        request.setStatus(requestDto.getStatus());
        request.setProject(project);

        ResourceRequest savedRequest = resourceRequestRepository.save(request);

        return ResourceRequestMapper.entityToResponseDto(savedRequest);
    }

    // Get Resource Request By Id
    public ResourceRequestResponseDto getResourceRequestById(Long requestId) {

        ResourceRequest request = resourceRequestRepository.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return ResourceRequestMapper.entityToResponseDto(request);
    }

    // Get All Resource Requests
    public List<ResourceRequestResponseDto> getAllResourceRequests() {

        return resourceRequestRepository.findAll()
                .stream()
                .map(ResourceRequestMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    // Update Resource Request
    public ResourceRequestResponseDto updateResourceRequest(
            Long requestId,
            ResourceRequestRequestDto requestDto) {

        ResourceRequest request = resourceRequestRepository.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Project project = projectRepository.findById(requestDto.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        ResourceRequestMapper.updateEntityFromRequestDto(requestDto, request);

        request.setProject(project);

        ResourceRequest updatedRequest = resourceRequestRepository.save(request);

        return ResourceRequestMapper.entityToResponseDto(updatedRequest);
    }

    // Delete Resource Request
    public void deleteResourceRequest(Long requestId) {

        ResourceRequest request = resourceRequestRepository.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        resourceRequestRepository.delete(request);
    }
}