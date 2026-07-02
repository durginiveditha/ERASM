package com.incture.erasm.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.incture.erasm.dto.request.AllocationRequestDto;
import com.incture.erasm.dto.response.AllocationResponseDto;
import com.incture.erasm.service.AllocationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/allocations")
public class AllocationController {

    private final AllocationService allocationService;

    public AllocationController(AllocationService allocationService) {
        this.allocationService = allocationService;
    }

    @PostMapping
    public ResponseEntity<AllocationResponseDto> createAllocation(
            @Valid @RequestBody AllocationRequestDto requestDto) {

        AllocationResponseDto response =
                allocationService.createAllocation(requestDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AllocationResponseDto> getAllocationById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                allocationService.getAllocationById(id));
    }

    @GetMapping
    public ResponseEntity<List<AllocationResponseDto>> getAllAllocations() {

        return ResponseEntity.ok(
                allocationService.getAllAllocations());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AllocationResponseDto> updateAllocation(
            @PathVariable Long id,
            @Valid @RequestBody AllocationRequestDto requestDto) {

        return ResponseEntity.ok(
                allocationService.updateAllocation(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAllocation(
            @PathVariable Long id) {

        allocationService.deleteAllocation(id);

        return ResponseEntity.ok("Allocation deleted successfully.");
    }
}