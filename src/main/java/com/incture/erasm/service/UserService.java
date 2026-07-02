package com.incture.erasm.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.incture.erasm.dto.request.UserRequestDto;
import com.incture.erasm.dto.response.UserResponseDto;
import com.incture.erasm.entity.User;
import com.incture.erasm.exception.ResourceNotFoundException;
import com.incture.erasm.mapper.UserMapper;
import com.incture.erasm.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Create User
    public UserResponseDto createUser(UserRequestDto requestDto) {

        User user = UserMapper.requestDtoToEntity(requestDto);

        User savedUser = userRepository.save(user);

        return UserMapper.entityToResponseDto(savedUser);
    }

    // Get User By Id
    public UserResponseDto getUserById(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return UserMapper.entityToResponseDto(user);
    }

    // Get All Users
    public List<UserResponseDto> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(UserMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    // Update User
    public UserResponseDto updateUser(Long userId, UserRequestDto requestDto) {

        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        existingUser.setFirstName(requestDto.getFirstName());
        existingUser.setLastName(requestDto.getLastName());
        existingUser.setEmail(requestDto.getEmail());
        existingUser.setPassword(requestDto.getPassword());
        existingUser.setActive(requestDto.isActive());

        User updatedUser = userRepository.save(existingUser);

        return UserMapper.entityToResponseDto(updatedUser);
    }

    // Delete User
    public void deleteUser(Long userId) {

        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        userRepository.delete(existingUser);
    }
}