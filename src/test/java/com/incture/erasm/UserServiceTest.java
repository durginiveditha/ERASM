package com.incture.erasm;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.incture.erasm.dto.request.UserRequestDto;
import com.incture.erasm.dto.response.UserResponseDto;
import com.incture.erasm.entity.User;
import com.incture.erasm.exception.UserNotFoundException;
import com.incture.erasm.repository.UserRepository;
import com.incture.erasm.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private User user;
    private UserRequestDto requestDto;

    @BeforeEach
    void setUp() {
        user = new User(1L, "John", "Doe", "john@example.com", "encodedPassword123", true);

        requestDto = new UserRequestDto("John", "Doe", "john@example.com", "password123", true);
    }

    // ── CREATE USER ──

    @Test
    void createUser_Success() {
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword123");
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserResponseDto result = userService.createUser(requestDto);

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        assertEquals("john@example.com", result.getEmail());
        assertTrue(result.isActive());

        verify(userRepository, times(1)).save(any(User.class));
        verify(passwordEncoder, times(1)).encode(anyString());
    }

    @Test
    void createUser_PasswordIsEncoded() {
        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword123");
        when(userRepository.save(any(User.class))).thenReturn(user);

        userService.createUser(requestDto);

        verify(passwordEncoder, times(1)).encode("password123");
    }

    // ── GET USER BY ID ──

    @Test
    void getUserById_Success() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        UserResponseDto result = userService.getUserById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getUserId());
        assertEquals("John", result.getFirstName());
        assertEquals("john@example.com", result.getEmail());
    }

    @Test
    void getUserById_UserNotFound_ThrowsException() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        UserNotFoundException exception = assertThrows(
            UserNotFoundException.class,
            () -> userService.getUserById(99L)
        );

        assertEquals("User not found", exception.getMessage());
    }

    // ── GET ALL USERS ──

    @Test
    void getAllUsers_ReturnsAllUsers() {
        User user2 = new User(2L, "Jane", "Smith", "jane@example.com", "pass", true);
        when(userRepository.findAll()).thenReturn(Arrays.asList(user, user2));

        List<UserResponseDto> result = userService.getAllUsers();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getFirstName());
        assertEquals("Jane", result.get(1).getFirstName());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void getAllUsers_EmptyList_ReturnsEmptyList() {
        when(userRepository.findAll()).thenReturn(Arrays.asList());

        List<UserResponseDto> result = userService.getAllUsers();

        assertNotNull(result);
        assertEquals(0, result.size());
    }

    // ── UPDATE USER ──

    @Test
    void updateUser_Success() {
        UserRequestDto updateDto = new UserRequestDto(
            "UpdatedFirst", "UpdatedLast", "updated@example.com", "newpass123", false
        );
        User updatedUser = new User(1L, "UpdatedFirst", "UpdatedLast",
                "updated@example.com", "encodedNewPass", false);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(passwordEncoder.encode("newpass123")).thenReturn("encodedNewPass");
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        UserResponseDto result = userService.updateUser(1L, updateDto);

        assertNotNull(result);
        assertEquals("UpdatedFirst", result.getFirstName());
        assertEquals("updated@example.com", result.getEmail());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void updateUser_UserNotFound_ThrowsException() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(
            UserNotFoundException.class,
            () -> userService.updateUser(99L, requestDto)
        );

        verify(userRepository, never()).save(any(User.class));
    }

    // ── DELETE USER ──

    @Test
    void deleteUser_Success() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        doNothing().when(userRepository).delete(user);

        assertDoesNotThrow(() -> userService.deleteUser(1L));

        verify(userRepository, times(1)).delete(user);
    }

    @Test
    void deleteUser_UserNotFound_ThrowsException() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(
            UserNotFoundException.class,
            () -> userService.deleteUser(99L)
        );

        verify(userRepository, never()).delete(any(User.class));
    }
}