package com.incture.erasm.mapper;

import com.incture.erasm.dto.request.UserRequestDto;
import com.incture.erasm.dto.response.UserResponseDto;
import com.incture.erasm.entity.User;

public class UserMapper {

    private UserMapper() {
    }

    // Convert Request DTO -> Entity
    public static User requestDtoToEntity(UserRequestDto dto) {

        User user = new User();

        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setActive(dto.isActive());

        return user;
    }

    // Convert Entity -> Response DTO
    public static UserResponseDto entityToResponseDto(User user) {

        UserResponseDto dto = new UserResponseDto();

        dto.setUserId(user.getUserId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setActive(user.isActive());

        return dto;
    }
}