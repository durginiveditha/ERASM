package com.incture.erasm.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.incture.erasm.dto.request.CertificationRequestDto;
import com.incture.erasm.dto.response.CertificationResponseDto;
import com.incture.erasm.service.CertificationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/certifications")
public class CertificationController {

    private final CertificationService certificationService;

    public CertificationController(CertificationService certificationService) {
        this.certificationService = certificationService;
    }

    // Create Certification
    @PostMapping
    public ResponseEntity<CertificationResponseDto> createCertification(
            @Valid @RequestBody CertificationRequestDto requestDto) {

        CertificationResponseDto response =
                certificationService.createCertification(requestDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get Certification By Id
    @GetMapping("/{id}")
    public ResponseEntity<CertificationResponseDto> getCertificationById(
            @PathVariable Long id) {

        return ResponseEntity.ok(certificationService.getCertificationById(id));
    }

    // Get All Certifications
    @GetMapping
    public ResponseEntity<List<CertificationResponseDto>> getAllCertifications() {

        return ResponseEntity.ok(certificationService.getAllCertifications());
    }

    // Update Certification
    @PutMapping("/{id}")
    public ResponseEntity<CertificationResponseDto> updateCertification(
            @PathVariable Long id,
            @Valid @RequestBody CertificationRequestDto requestDto) {

        return ResponseEntity.ok(
                certificationService.updateCertification(id, requestDto));
    }

    // Delete Certification
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCertification(
            @PathVariable Long id) {

        certificationService.deleteCertification(id);

        return ResponseEntity.ok("Certification deleted successfully.");
    }
}