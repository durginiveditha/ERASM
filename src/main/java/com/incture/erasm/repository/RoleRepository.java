package com.incture.erasm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.incture.erasm.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    // Find role by name
    Optional<Role> findByRoleName(String roleName);

    // Check if role already exists
    boolean existsByRoleName(String roleName);

}