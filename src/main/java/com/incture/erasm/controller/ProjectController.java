package com.incture.erasm.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.incture.erasm.dto.request.ProjectRequestDto;
import com.incture.erasm.dto.response.ProjectResponseDto;
import com.incture.erasm.service.ProjectService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/projects")
@PreAuthorize("hasAnyRole('ADMIN','DELIVERY_MANAGER')")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    // Create Project
    @PostMapping
    public ResponseEntity<ProjectResponseDto> createProject(
            @Valid @RequestBody ProjectRequestDto requestDto) {

        ProjectResponseDto response = projectService.createProject(requestDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get Project By Id
    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> getProjectById(
            @PathVariable Long id) {

        return ResponseEntity.ok(projectService.getProjectById(id));
    }

    // Get All Projects
    @GetMapping
    public ResponseEntity<List<ProjectResponseDto>> getAllProjects() {

        return ResponseEntity.ok(projectService.getAllProjects());
    }

    // Update Project
    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> updateProject(
            @PathVariable Long id,
            @Valid @RequestBody ProjectRequestDto requestDto) {

        return ResponseEntity.ok(
                projectService.updateProject(id, requestDto));
    }

    // Delete Project
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProject(
            @PathVariable Long id) {

        projectService.deleteProject(id);

        return ResponseEntity.ok("Project deleted successfully.");
    }
}