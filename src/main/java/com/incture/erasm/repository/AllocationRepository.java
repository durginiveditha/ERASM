package com.incture.erasm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.incture.erasm.entity.Allocation;

public interface AllocationRepository extends JpaRepository<Allocation, Long> {

}