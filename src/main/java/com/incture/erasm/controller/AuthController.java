package com.incture.erasm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.incture.erasm.dto.request.LoginRequestDto;
import com.incture.erasm.dto.request.UserRequestDto;
import com.incture.erasm.dto.response.LoginResponseDto;
import com.incture.erasm.dto.response.UserResponseDto;
import com.incture.erasm.service.AuthService;
import com.incture.erasm.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;
    private final UserService userService;

    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(
            @RequestBody LoginRequestDto requestDto) {
        logger.info("Login attempt for email: {}", requestDto.getEmail());
        return ResponseEntity.ok(authService.login(requestDto));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(
            @RequestBody UserRequestDto requestDto) {
        logger.info("Register request for email: {}", requestDto.getEmail());
        UserResponseDto response = userService.createUser(requestDto);
        logger.info("User registered successfully with email: {}", requestDto.getEmail());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}