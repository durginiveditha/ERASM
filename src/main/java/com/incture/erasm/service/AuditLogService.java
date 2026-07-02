package com.incture.erasm.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.incture.erasm.dto.request.AuditLogRequestDto;
import com.incture.erasm.dto.response.AuditLogResponseDto;
import com.incture.erasm.entity.AuditLog;
import com.incture.erasm.mapper.AuditLogMapper;
import com.incture.erasm.repository.AuditLogRepository;

import com.incture.erasm.exception.ResourceNotFoundException;

@Service
public class AuditLogService {

    private final AuditLogRepository auditLogRepository;

    public AuditLogService(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    // Create
    public AuditLogResponseDto createAuditLog(AuditLogRequestDto requestDto) {

        AuditLog auditLog = AuditLogMapper.requestDtoToEntity(requestDto);

        AuditLog savedAuditLog = auditLogRepository.save(auditLog);

        return AuditLogMapper.entityToResponseDto(savedAuditLog);
    }

    // Get By Id
    public AuditLogResponseDto getAuditLogById(Long auditId) {

        AuditLog auditLog = auditLogRepository.findById(auditId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return AuditLogMapper.entityToResponseDto(auditLog);
    }

    // Get All
    public List<AuditLogResponseDto> getAllAuditLogs() {

        return auditLogRepository.findAll()
                .stream()
                .map(AuditLogMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    // Update
    public AuditLogResponseDto updateAuditLog(Long auditId,
                                              AuditLogRequestDto requestDto) {

        AuditLog auditLog = auditLogRepository.findById(auditId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        AuditLogMapper.updateEntityFromRequestDto(requestDto, auditLog);

        AuditLog updatedAuditLog = auditLogRepository.save(auditLog);

        return AuditLogMapper.entityToResponseDto(updatedAuditLog);
    }

    // Delete
    public void deleteAuditLog(Long auditId) {

        AuditLog auditLog = auditLogRepository.findById(auditId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        auditLogRepository.delete(auditLog);
    }
}