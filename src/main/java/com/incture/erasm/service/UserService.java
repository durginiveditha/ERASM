package com.incture.erasm.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.incture.erasm.dto.request.UserRequestDto;
import com.incture.erasm.dto.response.UserResponseDto;
import com.incture.erasm.entity.User;
import com.incture.erasm.exception.UserNotFoundException;
import com.incture.erasm.mapper.UserMapper;
import com.incture.erasm.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserService {

	private static final Logger logger =
            LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Create User
    public UserResponseDto createUser(UserRequestDto requestDto) {

    	logger.info("Creating user with email: {}", requestDto.getEmail());
        User user = UserMapper.requestDtoToEntity(requestDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        logger.info("Creating user with email: {}", requestDto.getEmail());
        return UserMapper.entityToResponseDto(savedUser);
    }

    // Get User By Id
    public UserResponseDto getUserById(Long userId) {

        User user = userRepository.findById(userId)
        		.orElseThrow(() -> {

                    logger.warn("User not found with ID: {}", userId);

                    return new UserNotFoundException("User not found");
                });

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

    	logger.info("Updating user with ID: {}", userId);
        User existingUser = userRepository.findById(userId)
        		.orElseThrow(() -> {

                    logger.warn("User not found with ID: {}", userId);

                    return new UserNotFoundException("User not found");
                });

        existingUser.setFirstName(requestDto.getFirstName());
        existingUser.setLastName(requestDto.getLastName());
        existingUser.setEmail(requestDto.getEmail());
        existingUser.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        existingUser.setActive(requestDto.isActive());

        User updatedUser = userRepository.save(existingUser);
        logger.info("User updated successfully. User ID: {}", updatedUser.getUserId());
        return UserMapper.entityToResponseDto(updatedUser);
    }

    // Delete User
    public void deleteUser(Long userId) {
    	logger.info("Deleting user with ID: {}", userId);
        User existingUser = userRepository.findById(userId)
        		.orElseThrow(() -> {

                    logger.warn("User not found with ID: {}", userId);

                    return new UserNotFoundException("User not found");
                });

        userRepository.delete(existingUser);
        logger.info("User deleted successfully. User ID: {}", userId);
    }
}