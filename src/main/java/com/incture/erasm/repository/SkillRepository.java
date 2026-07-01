package com.incture.erasm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.incture.erasm.entity.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long> {

    // Find skill by name
    Optional<Skill> findBySkillName(String skillName);

    // Check if skill exists
    boolean existsBySkillName(String skillName);

}