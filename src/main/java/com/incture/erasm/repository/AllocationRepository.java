package com.incture.erasm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.incture.erasm.entity.Allocation;
import com.incture.erasm.entity.Employee;

public interface AllocationRepository extends JpaRepository<Allocation, Long> {

	List<Allocation> findByEmployee(Employee employee);
	
}