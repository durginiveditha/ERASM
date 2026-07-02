package com.incture.erasm.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.incture.erasm.dto.request.ResourceRequestRequestDto;
import com.incture.erasm.dto.response.ResourceRequestResponseDto;
import com.incture.erasm.service.ResourceRequestService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/resource-requests")
public class ResourceRequestController {

    private final ResourceRequestService resourceRequestService;

    public ResourceRequestController(ResourceRequestService resourceRequestService) {
        this.resourceRequestService = resourceRequestService;
    }

    // Create Resource Request
    @PostMapping
    public ResponseEntity<ResourceRequestResponseDto> createResourceRequest(
            @Valid @RequestBody ResourceRequestRequestDto requestDto) {

        ResourceRequestResponseDto response =
                resourceRequestService.createResourceRequest(requestDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get Resource Request By Id
    @GetMapping("/{id}")
    public ResponseEntity<ResourceRequestResponseDto> getResourceRequestById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                resourceRequestService.getResourceRequestById(id));
    }

    // Get All Resource Requests
    @GetMapping
    public ResponseEntity<List<ResourceRequestResponseDto>> getAllResourceRequests() {

        return ResponseEntity.ok(
                resourceRequestService.getAllResourceRequests());
    }

    // Update Resource Request
    @PutMapping("/{id}")
    public ResponseEntity<ResourceRequestResponseDto> updateResourceRequest(
            @PathVariable Long id,
            @Valid @RequestBody ResourceRequestRequestDto requestDto) {

        return ResponseEntity.ok(
                resourceRequestService.updateResourceRequest(id, requestDto));
    }

    // Delete Resource Request
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteResourceRequest(
            @PathVariable Long id) {

        resourceRequestService.deleteResourceRequest(id);

        return ResponseEntity.ok("Resource Request deleted successfully.");
    }
}