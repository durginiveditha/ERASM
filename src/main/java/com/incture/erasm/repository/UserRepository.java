package com.incture.erasm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.incture.erasm.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    // Find user by email (used during login)
    Optional<User> findByEmail(String email);

    // Check if email already exists 
    boolean existsByEmail(String email);

}