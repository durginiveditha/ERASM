package com.incture.erasm;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.incture.erasm.dto.request.AllocationRequestDto;
import com.incture.erasm.dto.response.AllocationResponseDto;
import com.incture.erasm.entity.Allocation;
import com.incture.erasm.entity.Employee;
import com.incture.erasm.entity.Project;
import com.incture.erasm.exception.AllocationException;
import com.incture.erasm.exception.ResourceNotFoundException;
import com.incture.erasm.repository.AllocationRepository;
import com.incture.erasm.repository.EmployeeRepository;
import com.incture.erasm.repository.ProjectRepository;
import com.incture.erasm.service.AllocationService;
import com.incture.erasm.entity.User;
import com.incture.erasm.entity.Role;
import com.incture.erasm.entity.User;

@ExtendWith(MockitoExtension.class)
public class AllocationServiceTest {

    @Mock
    private AllocationRepository allocationRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private AllocationService allocationService;

    private Employee employee;
    private Project project;
    private Allocation allocation;
    private AllocationRequestDto requestDto;

    @BeforeEach
    void setUp() {
        User user = new User(1L, "John", "Doe", "john@example.com", "pass", true);

        Role role = new Role();          // ← add this
        role.setRoleId(1L);              // ← add this
        role.setRoleName("EMPLOYEE");    // ← add this

        employee = new Employee();
        employee.setEmployeeId(1L);
        employee.setEmployeeCode("EMP001");
        employee.setDepartment("Engineering");
        employee.setDesignation("Developer");
        employee.setExperience(3);
        employee.setAvailability(true);
        employee.setUser(user);
        employee.setRole(role);          // ← add this

        project = new Project();
        project.setProjectId(1L);
        project.setProjectName("Test Project");
        project.setClientName("Test Client");
        project.setStartDate("2025-01-01");
        project.setEndDate("2025-12-31");
        project.setTechnologyStack("Java, Spring Boot");
        project.setBudget(100000.0);

        allocation = new Allocation(1L, employee, project, 50.0,
                "2025-01-01", "2025-06-30", "ACTIVE");

        requestDto = new AllocationRequestDto(1L, 1L, 50.0,
                "2025-01-01", "2025-06-30", "ACTIVE");
    }

    //CREATE ALLOCATION

    @Test
    void createAllocation_Success() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        when(allocationRepository.findByEmployee(employee)).thenReturn(Collections.emptyList());
        when(allocationRepository.save(any(Allocation.class))).thenReturn(allocation);

        AllocationResponseDto result = allocationService.createAllocation(requestDto);

        assertNotNull(result);
        assertEquals(50.0, result.getAllocationPercentage());
        assertEquals("ACTIVE", result.getStatus());
        verify(allocationRepository, times(1)).save(any(Allocation.class));
    }

    @Test
    void createAllocation_ExceedsHundredPercent_ThrowsException() {
        Allocation existingAllocation = new Allocation(2L, employee, project,
                70.0, "2025-01-01", null, "ACTIVE");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        when(allocationRepository.findByEmployee(employee))
                .thenReturn(Arrays.asList(existingAllocation));

        // 70 existing + 50 new = 120 — should throw
        AllocationException exception = assertThrows(
            AllocationException.class,
            () -> allocationService.createAllocation(requestDto)
        );

        assertEquals("Employee allocation cannot exceed 100%", exception.getMessage());
        verify(allocationRepository, never()).save(any(Allocation.class));
    }

    @Test
    void createAllocation_ExactlyHundredPercent_Success() {
        Allocation existingAllocation = new Allocation(2L, employee, project,
                50.0, "2025-01-01", null, "ACTIVE");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        when(allocationRepository.findByEmployee(employee))
                .thenReturn(Arrays.asList(existingAllocation));
        when(allocationRepository.save(any(Allocation.class))).thenReturn(allocation);

        // 50 existing + 50 new = exactly 100 — should succeed
        assertDoesNotThrow(() -> allocationService.createAllocation(requestDto));
        verify(allocationRepository, times(1)).save(any(Allocation.class));
    }

    @Test
    void createAllocation_EmployeeNotFound_ThrowsException() {
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(
            ResourceNotFoundException.class,
            () -> allocationService.createAllocation(requestDto)
        );

        verify(allocationRepository, never()).save(any(Allocation.class));
    }

    @Test
    void createAllocation_ProjectNotFound_ThrowsException() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(projectRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(
            Exception.class,
            () -> allocationService.createAllocation(requestDto)
        );

        verify(allocationRepository, never()).save(any(Allocation.class));
    }

    //GET ALLOCATION BY ID

    @Test
    void getAllocationById_Success() {
        when(allocationRepository.findById(1L)).thenReturn(Optional.of(allocation));

        AllocationResponseDto result = allocationService.getAllocationById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getAllocationId());
        assertEquals(50.0, result.getAllocationPercentage());
    }

    @Test
    void getAllocationById_NotFound_ThrowsException() {
        when(allocationRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(
            AllocationException.class,
            () -> allocationService.getAllocationById(99L)
        );
    }

    //GET ALL ALLOCATIONS

    @Test
    void getAllAllocations_ReturnsAllAllocations() {
        Allocation allocation2 = new Allocation(2L, employee, project,
                30.0, "2025-02-01", null, "ACTIVE");

        when(allocationRepository.findAll()).thenReturn(Arrays.asList(allocation, allocation2));

        List<AllocationResponseDto> result = allocationService.getAllAllocations();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(allocationRepository, times(1)).findAll();
    }

    //DELETE ALLOCATION

    @Test
    void deleteAllocation_Success() {
        when(allocationRepository.findById(1L)).thenReturn(Optional.of(allocation));
        doNothing().when(allocationRepository).delete(allocation);

        assertDoesNotThrow(() -> allocationService.deleteAllocation(1L));

        verify(allocationRepository, times(1)).delete(allocation);
    }

    @Test
    void deleteAllocation_NotFound_ThrowsException() {
        when(allocationRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(
            AllocationException.class,
            () -> allocationService.deleteAllocation(99L)
        );

        verify(allocationRepository, never()).delete(any(Allocation.class));
    }
}