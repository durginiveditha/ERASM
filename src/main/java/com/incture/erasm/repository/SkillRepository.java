package com.incture.erasm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.incture.erasm.entity.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long> {

    Optional<Skill> findBySkillName(String skillName);

    boolean existsBySkillName(String skillName);

}