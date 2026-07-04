package com.incture.erasm.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.incture.erasm.dto.response.AllocationReportDto;
import com.incture.erasm.dto.response.SkillReportDto;
import com.incture.erasm.dto.response.UtilizationReportDto;
import com.incture.erasm.service.ReportService;

@RestController
@RequestMapping("/api/reports")
@PreAuthorize("hasAnyRole('ADMIN','RESOURCE_MANAGER','DELIVERY_MANAGER')")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/skills")
    public List<SkillReportDto> getSkillReport() {
        return reportService.getSkillReport();
    }

    @GetMapping("/allocations")
    public List<AllocationReportDto> getAllocationReport() {
        return reportService.getAllocationReport();
    }

    @GetMapping("/utilization")
    public List<UtilizationReportDto> getUtilizationReport() {
        return reportService.getUtilizationReport();
    }
}