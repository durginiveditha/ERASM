package com.incture.erasm.mapper;

import com.incture.erasm.dto.request.ResourceRequestRequestDto;
import com.incture.erasm.dto.response.ResourceRequestResponseDto;
import com.incture.erasm.entity.ResourceRequest;

public class ResourceRequestMapper {

    private ResourceRequestMapper() {
    }

    // Entity -> Response DTO
    public static ResourceRequestResponseDto entityToResponseDto(ResourceRequest request) {

        ResourceRequestResponseDto responseDto = new ResourceRequestResponseDto();

        responseDto.setRequestId(request.getRequestId());
        responseDto.setRequiredSkill(request.getRequiredSkill());
        responseDto.setRequiredCount(request.getRequiredCount());
        responseDto.setStatus(request.getStatus());

        responseDto.setProject(
                ProjectMapper.entityToResponseDto(request.getProject()));

        return responseDto;
    }

    // Update Entity
    public static void updateEntityFromRequestDto(
            ResourceRequestRequestDto requestDto,
            ResourceRequest request) {

        request.setRequiredSkill(requestDto.getRequiredSkill());
        request.setRequiredCount(requestDto.getRequiredCount());
        request.setStatus(requestDto.getStatus());
    }
}