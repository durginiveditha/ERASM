package com.incture.erasm.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.incture.erasm.dto.response.AllocationReportDto;
import com.incture.erasm.dto.response.SkillReportDto;
import com.incture.erasm.dto.response.UtilizationReportDto;
import com.incture.erasm.entity.Allocation;
import com.incture.erasm.entity.Skill;
import com.incture.erasm.repository.AllocationRepository;
import com.incture.erasm.repository.SkillRepository;

@Service
public class ReportService {

    private final SkillRepository skillRepository;
    private final AllocationRepository allocationRepository;

    public ReportService(SkillRepository skillRepository,
                         AllocationRepository allocationRepository) {

        this.skillRepository = skillRepository;
        this.allocationRepository = allocationRepository;
    }

    // Skill Report
    public List<SkillReportDto> getSkillReport() {

        List<Skill> skills = skillRepository.findAll();

        return skills.stream()
                .map(skill -> new SkillReportDto(
                        skill.getSkillId(),
                        skill.getSkillName()))
                .collect(Collectors.toList());
    }

    // Allocation Report
    public List<AllocationReportDto> getAllocationReport() {

        List<Allocation> allocations = allocationRepository.findAll();

        return allocations.stream()
                .map(allocation -> new AllocationReportDto(
                        allocation.getEmployee().getEmployeeCode(),
                        allocation.getProject().getProjectName(),
                        allocation.getAllocationPercentage()))
                .collect(Collectors.toList());
    }

    // Utilization Report
    public List<UtilizationReportDto> getUtilizationReport() {

        List<Allocation> allocations = allocationRepository.findAll();

        return allocations.stream()
                .map(allocation -> new UtilizationReportDto(
                        allocation.getEmployee().getEmployeeCode(),
                        allocation.getAllocationPercentage()))
                .collect(Collectors.toList());
    }
}