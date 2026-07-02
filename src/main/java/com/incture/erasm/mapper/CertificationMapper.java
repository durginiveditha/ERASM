package com.incture.erasm.mapper;

import com.incture.erasm.dto.request.CertificationRequestDto;
import com.incture.erasm.dto.response.CertificationResponseDto;
import com.incture.erasm.entity.Certification;

public class CertificationMapper {

    private CertificationMapper() {
    }

    // Entity -> Response DTO
    public static CertificationResponseDto entityToResponseDto(Certification certification) {

        CertificationResponseDto responseDto = new CertificationResponseDto();

        responseDto.setCertificationId(certification.getCertificationId());
        responseDto.setCertificationName(certification.getCertificationName());
        responseDto.setIssuingOrganization(certification.getIssuingOrganization());
        responseDto.setIssueDate(certification.getIssueDate());
        responseDto.setExpiryDate(certification.getExpiryDate());

        responseDto.setEmployee(
                EmployeeMapper.entityToResponseDto(certification.getEmployee()));

        return responseDto;
    }

    // Update Entity
    public static void updateEntityFromRequestDto(
            CertificationRequestDto requestDto,
            Certification certification) {

        certification.setCertificationName(requestDto.getCertificationName());
        certification.setIssuingOrganization(requestDto.getIssuingOrganization());
        certification.setIssueDate(requestDto.getIssueDate());
        certification.setExpiryDate(requestDto.getExpiryDate());
    }
}