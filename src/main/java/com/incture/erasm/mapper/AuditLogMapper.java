package com.incture.erasm.mapper;

import com.incture.erasm.dto.request.AuditLogRequestDto;
import com.incture.erasm.dto.response.AuditLogResponseDto;
import com.incture.erasm.entity.AuditLog;

public class AuditLogMapper {

    private AuditLogMapper() {
    }

    // Request DTO -> Entity
    public static AuditLog requestDtoToEntity(AuditLogRequestDto requestDto) {

        AuditLog auditLog = new AuditLog();

        auditLog.setAction(requestDto.getAction());
        auditLog.setCreatedBy(requestDto.getCreatedBy());
        auditLog.setModifiedBy(requestDto.getModifiedBy());
        auditLog.setCreatedDate(requestDto.getCreatedDate());
        auditLog.setModifiedDate(requestDto.getModifiedDate());

        return auditLog;
    }

    // Entity -> Response DTO
    public static AuditLogResponseDto entityToResponseDto(AuditLog auditLog) {

        AuditLogResponseDto responseDto = new AuditLogResponseDto();

        responseDto.setAuditId(auditLog.getAuditId());
        responseDto.setAction(auditLog.getAction());
        responseDto.setCreatedBy(auditLog.getCreatedBy());
        responseDto.setModifiedBy(auditLog.getModifiedBy());
        responseDto.setCreatedDate(auditLog.getCreatedDate());
        responseDto.setModifiedDate(auditLog.getModifiedDate());

        return responseDto;
    }

    // Update Entity
    public static void updateEntityFromRequestDto(
            AuditLogRequestDto requestDto,
            AuditLog auditLog) {

        auditLog.setAction(requestDto.getAction());
        auditLog.setCreatedBy(requestDto.getCreatedBy());
        auditLog.setModifiedBy(requestDto.getModifiedBy());
        auditLog.setCreatedDate(requestDto.getCreatedDate());
        auditLog.setModifiedDate(requestDto.getModifiedDate());
    }
}