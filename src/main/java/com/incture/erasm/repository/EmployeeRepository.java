package com.incture.erasm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.incture.erasm.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Find employee by employee code
    Optional<Employee> findByEmployeeCode(String employeeCode);

    // Check if employee code already exists
    boolean existsByEmployeeCode(String employeeCode);

}