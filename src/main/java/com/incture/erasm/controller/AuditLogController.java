package com.incture.erasm.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.incture.erasm.dto.request.AuditLogRequestDto;
import com.incture.erasm.dto.response.AuditLogResponseDto;
import com.incture.erasm.service.AuditLogService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/audit-logs")
public class AuditLogController {

    private final AuditLogService auditLogService;

    public AuditLogController(AuditLogService auditLogService) {
        this.auditLogService = auditLogService;
    }

    @PostMapping
    public ResponseEntity<AuditLogResponseDto> createAuditLog(
            @Valid @RequestBody AuditLogRequestDto requestDto) {

        return new ResponseEntity<>(
                auditLogService.createAuditLog(requestDto),
                HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuditLogResponseDto> getAuditLogById(@PathVariable Long id) {

        return ResponseEntity.ok(auditLogService.getAuditLogById(id));
    }

    @GetMapping
    public ResponseEntity<List<AuditLogResponseDto>> getAllAuditLogs() {

        return ResponseEntity.ok(auditLogService.getAllAuditLogs());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuditLogResponseDto> updateAuditLog(
            @PathVariable Long id,
            @Valid @RequestBody AuditLogRequestDto requestDto) {

        return ResponseEntity.ok(
                auditLogService.updateAuditLog(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuditLog(@PathVariable Long id) {

        auditLogService.deleteAuditLog(id);

        return ResponseEntity.ok("Audit Log deleted successfully.");
    }
}