package com.incture.erasm.mapper;

import com.incture.erasm.dto.request.RoleRequestDto;
import com.incture.erasm.dto.response.RoleResponseDto;
import com.incture.erasm.entity.Role;

public class RoleMapper {

    private RoleMapper() {
    }

    // Request DTO -> Entity
    public static Role requestDtoToEntity(RoleRequestDto requestDto) {

        Role role = new Role();
        role.setRoleName(requestDto.getRoleName());

        return role;
    }

    // Entity -> Response DTO
    public static RoleResponseDto entityToResponseDto(Role role) {

        RoleResponseDto responseDto = new RoleResponseDto();

        responseDto.setRoleId(role.getRoleId());
        responseDto.setRoleName(role.getRoleName());

        return responseDto;
    }

    // Update Entity
    public static void updateEntityFromRequestDto(RoleRequestDto requestDto,
                                                  Role role) {

        role.setRoleName(requestDto.getRoleName());
    }
}