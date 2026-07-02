package com.incture.erasm.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.incture.erasm.dto.request.CertificationRequestDto;
import com.incture.erasm.dto.response.CertificationResponseDto;
import com.incture.erasm.entity.Certification;
import com.incture.erasm.entity.Employee;
import com.incture.erasm.mapper.CertificationMapper;
import com.incture.erasm.repository.CertificationRepository;
import com.incture.erasm.repository.EmployeeRepository;

import com.incture.erasm.exception.ResourceNotFoundException;

@Service
public class CertificationService {

    private final CertificationRepository certificationRepository;
    private final EmployeeRepository employeeRepository;

    public CertificationService(CertificationRepository certificationRepository,
                                EmployeeRepository employeeRepository) {
        this.certificationRepository = certificationRepository;
        this.employeeRepository = employeeRepository;
    }

    // Create
    public CertificationResponseDto createCertification(CertificationRequestDto requestDto) {

        Employee employee = employeeRepository.findById(requestDto.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Certification certification = new Certification();

        certification.setCertificationName(requestDto.getCertificationName());
        certification.setIssuingOrganization(requestDto.getIssuingOrganization());
        certification.setIssueDate(requestDto.getIssueDate());
        certification.setExpiryDate(requestDto.getExpiryDate());
        certification.setEmployee(employee);

        Certification savedCertification = certificationRepository.save(certification);

        return CertificationMapper.entityToResponseDto(savedCertification);
    }

    // Get By Id
    public CertificationResponseDto getCertificationById(Long certificationId) {

        Certification certification = certificationRepository.findById(certificationId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return CertificationMapper.entityToResponseDto(certification);
    }

    // Get All
    public List<CertificationResponseDto> getAllCertifications() {

        return certificationRepository.findAll()
                .stream()
                .map(CertificationMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    // Update
    public CertificationResponseDto updateCertification(
            Long certificationId,
            CertificationRequestDto requestDto) {

        Certification certification = certificationRepository.findById(certificationId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Employee employee = employeeRepository.findById(requestDto.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        CertificationMapper.updateEntityFromRequestDto(requestDto, certification);

        certification.setEmployee(employee);

        Certification updatedCertification = certificationRepository.save(certification);

        return CertificationMapper.entityToResponseDto(updatedCertification);
    }

    // Delete
    public void deleteCertification(Long certificationId) {

        Certification certification = certificationRepository.findById(certificationId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        certificationRepository.delete(certification);
    }
}