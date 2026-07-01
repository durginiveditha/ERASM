package com.incture.erasm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.incture.erasm.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    // Find project by name
    Optional<Project> findByProjectName(String projectName);

}