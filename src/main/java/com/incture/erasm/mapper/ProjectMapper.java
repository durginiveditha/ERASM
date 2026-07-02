package com.incture.erasm.mapper;

import com.incture.erasm.dto.request.ProjectRequestDto;
import com.incture.erasm.dto.response.ProjectResponseDto;
import com.incture.erasm.entity.Project;

public class ProjectMapper {

    private ProjectMapper() {
    }

    // Request DTO -> Entity
    public static Project requestDtoToEntity(ProjectRequestDto requestDto) {

        Project project = new Project();

        project.setProjectName(requestDto.getProjectName());
        project.setClientName(requestDto.getClientName());
        project.setStartDate(requestDto.getStartDate());
        project.setEndDate(requestDto.getEndDate());
        project.setTechnologyStack(requestDto.getTechnologyStack());
        project.setBudget(requestDto.getBudget());

        return project;
    }

    // Entity -> Response DTO
    public static ProjectResponseDto entityToResponseDto(Project project) {

        ProjectResponseDto responseDto = new ProjectResponseDto();

        responseDto.setProjectId(project.getProjectId());
        responseDto.setProjectName(project.getProjectName());
        responseDto.setClientName(project.getClientName());
        responseDto.setStartDate(project.getStartDate());
        responseDto.setEndDate(project.getEndDate());
        responseDto.setTechnologyStack(project.getTechnologyStack());
        responseDto.setBudget(project.getBudget());

        return responseDto;
    }

    // Update Entity
    public static void updateEntityFromRequestDto(
            ProjectRequestDto requestDto,
            Project project) {

        project.setProjectName(requestDto.getProjectName());
        project.setClientName(requestDto.getClientName());
        project.setStartDate(requestDto.getStartDate());
        project.setEndDate(requestDto.getEndDate());
        project.setTechnologyStack(requestDto.getTechnologyStack());
        project.setBudget(requestDto.getBudget());
    }
}