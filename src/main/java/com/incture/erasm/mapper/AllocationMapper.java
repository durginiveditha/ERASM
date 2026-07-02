package com.incture.erasm.mapper;

import com.incture.erasm.dto.request.AllocationRequestDto;
import com.incture.erasm.dto.response.AllocationResponseDto;
import com.incture.erasm.entity.Allocation;

public class AllocationMapper {

    private AllocationMapper() {
    }

    // Entity -> Response DTO
    public static AllocationResponseDto entityToResponseDto(Allocation allocation) {

        AllocationResponseDto responseDto = new AllocationResponseDto();

        responseDto.setAllocationId(allocation.getAllocationId());
        responseDto.setAllocationPercentage(allocation.getAllocationPercentage());
        responseDto.setAllocationDate(allocation.getAllocationDate());
        responseDto.setReleaseDate(allocation.getReleaseDate());
        responseDto.setStatus(allocation.getStatus());

        responseDto.setEmployee(
                EmployeeMapper.entityToResponseDto(allocation.getEmployee()));

        responseDto.setProject(
                ProjectMapper.entityToResponseDto(allocation.getProject()));

        return responseDto;
    }

    // Update Entity
    public static void updateEntityFromRequestDto(
            AllocationRequestDto requestDto,
            Allocation allocation) {

        allocation.setAllocationPercentage(requestDto.getAllocationPercentage());
        allocation.setAllocationDate(requestDto.getAllocationDate());
        allocation.setReleaseDate(requestDto.getReleaseDate());
        allocation.setStatus(requestDto.getStatus());
    }
}