package com.incture.erasm;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.incture.erasm.dto.request.ProjectRequestDto;
import com.incture.erasm.dto.response.ProjectResponseDto;
import com.incture.erasm.entity.Project;
import com.incture.erasm.exception.ProjectNotFoundException;
import com.incture.erasm.repository.ProjectRepository;
import com.incture.erasm.service.ProjectService;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectService projectService;

    private Project project;
    private ProjectRequestDto requestDto;

    @BeforeEach
    void setUp() {
        project = new Project();
        project.setProjectId(1L);
        project.setProjectName("ERASM Project");
        project.setClientName("Incture");
        project.setStartDate("2025-01-01");
        project.setEndDate("2025-12-31");
        project.setTechnologyStack("Java, Spring Boot, MySQL");
        project.setBudget(500000.0);

        requestDto = new ProjectRequestDto(
            "ERASM Project", "Incture",
            "2025-01-01", "2025-12-31",
            "Java, Spring Boot, MySQL", 500000.0
        );
    }

    // ── CREATE PROJECT ──

    @Test
    void createProject_Success() {
        when(projectRepository.save(any(Project.class))).thenReturn(project);

        ProjectResponseDto result = projectService.createProject(requestDto);

        assertNotNull(result);
        assertEquals("ERASM Project", result.getProjectName());
        assertEquals("Incture", result.getClientName());
        assertEquals(500000.0, result.getBudget());
        verify(projectRepository, times(1)).save(any(Project.class));
    }

    @Test
    void createProject_SaveCalledOnce() {
        when(projectRepository.save(any(Project.class))).thenReturn(project);

        projectService.createProject(requestDto);

        verify(projectRepository, times(1)).save(any(Project.class));
    }

    // ── GET PROJECT BY ID ──

    @Test
    void getProjectById_Success() {
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        ProjectResponseDto result = projectService.getProjectById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getProjectId());
        assertEquals("ERASM Project", result.getProjectName());
        assertEquals("Incture", result.getClientName());
    }

    @Test
    void getProjectById_NotFound_ThrowsException() {
        when(projectRepository.findById(anyLong())).thenReturn(Optional.empty());

        ProjectNotFoundException exception = assertThrows(
            ProjectNotFoundException.class,
            () -> projectService.getProjectById(99L)
        );

        assertEquals("Project not found", exception.getMessage());
    }

    // ── GET ALL PROJECTS ──

    @Test
    void getAllProjects_ReturnsAllProjects() {
        Project project2 = new Project();
        project2.setProjectId(2L);
        project2.setProjectName("Second Project");
        project2.setClientName("Client B");
        project2.setStartDate("2025-03-01");
        project2.setEndDate("2025-09-30");
        project2.setTechnologyStack("React, Node.js");
        project2.setBudget(200000.0);

        when(projectRepository.findAll()).thenReturn(Arrays.asList(project, project2));

        List<ProjectResponseDto> result = projectService.getAllProjects();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("ERASM Project", result.get(0).getProjectName());
        assertEquals("Second Project", result.get(1).getProjectName());
        verify(projectRepository, times(1)).findAll();
    }

    @Test
    void getAllProjects_EmptyList_ReturnsEmptyList() {
        when(projectRepository.findAll()).thenReturn(Arrays.asList());

        List<ProjectResponseDto> result = projectService.getAllProjects();

        assertNotNull(result);
        assertEquals(0, result.size());
    }

    // ── UPDATE PROJECT ──

    @Test
    void updateProject_Success() {
        ProjectRequestDto updateDto = new ProjectRequestDto(
            "Updated Project", "New Client",
            "2025-06-01", "2026-06-01",
            "Python, Django", 750000.0
        );
        Project updatedProject = new Project();
        updatedProject.setProjectId(1L);
        updatedProject.setProjectName("Updated Project");
        updatedProject.setClientName("New Client");
        updatedProject.setStartDate("2025-06-01");
        updatedProject.setEndDate("2026-06-01");
        updatedProject.setTechnologyStack("Python, Django");
        updatedProject.setBudget(750000.0);

        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        when(projectRepository.save(any(Project.class))).thenReturn(updatedProject);

        ProjectResponseDto result = projectService.updateProject(1L, updateDto);

        assertNotNull(result);
        assertEquals("Updated Project", result.getProjectName());
        assertEquals("New Client", result.getClientName());
        assertEquals(750000.0, result.getBudget());
        verify(projectRepository, times(1)).save(any(Project.class));
    }

    @Test
    void updateProject_NotFound_ThrowsException() {
        when(projectRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(
            ProjectNotFoundException.class,
            () -> projectService.updateProject(99L, requestDto)
        );

        verify(projectRepository, never()).save(any(Project.class));
    }

    // ── DELETE PROJECT ──

    @Test
    void deleteProject_Success() {
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        doNothing().when(projectRepository).delete(project);

        assertDoesNotThrow(() -> projectService.deleteProject(1L));

        verify(projectRepository, times(1)).delete(project);
    }

    @Test
    void deleteProject_NotFound_ThrowsException() {
        when(projectRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(
            ProjectNotFoundException.class,
            () -> projectService.deleteProject(99L)
        );

        verify(projectRepository, never()).delete(any(Project.class));
    }
}