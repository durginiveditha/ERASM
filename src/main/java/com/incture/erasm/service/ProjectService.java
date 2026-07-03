package com.incture.erasm.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.incture.erasm.dto.request.ProjectRequestDto;
import com.incture.erasm.dto.response.ProjectResponseDto;
import com.incture.erasm.entity.Project;
import com.incture.erasm.mapper.ProjectMapper;
import com.incture.erasm.repository.ProjectRepository;

import com.incture.erasm.exception.ResourceNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ProjectService {

	private static final Logger logger =
	        LoggerFactory.getLogger(ProjectService.class);
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    // Create
    public ProjectResponseDto createProject(ProjectRequestDto requestDto) {
    	logger.info("Creating project: {}", requestDto.getProjectName());
        Project project = ProjectMapper.requestDtoToEntity(requestDto);

        Project savedProject = projectRepository.save(project);
        logger.info("Project created successfully. Project ID: {}", savedProject.getProjectId());
        return ProjectMapper.entityToResponseDto(savedProject);
    }

    // Get By Id
    public ProjectResponseDto getProjectById(Long projectId) {

        Project project = projectRepository.findById(projectId)
        		.orElseThrow(() -> {

        		    logger.warn("Project not found with ID: {}", projectId);

        		    return new ResourceNotFoundException("Project not found");
        		});

        return ProjectMapper.entityToResponseDto(project);
    }

    // Get All
    public List<ProjectResponseDto> getAllProjects() {

        return projectRepository.findAll()
                .stream()
                .map(ProjectMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    // Update
    public ProjectResponseDto updateProject(
            Long projectId,
            ProjectRequestDto requestDto) {

        Project project = projectRepository.findById(projectId)
        		.orElseThrow(() -> {

        		    logger.warn("Project not found with ID: {}", projectId);

        		    return new ResourceNotFoundException("Project not found");
        		});

        ProjectMapper.updateEntityFromRequestDto(requestDto, project);

        Project updatedProject = projectRepository.save(project);

        return ProjectMapper.entityToResponseDto(updatedProject);
    }

    // Delete
    public void deleteProject(Long projectId) {

        Project project = projectRepository.findById(projectId)
        		.orElseThrow(() -> {

        		    logger.warn("Project not found with ID: {}", projectId);

        		    return new ResourceNotFoundException("Project not found");
        		});

        projectRepository.delete(project);
    }
}