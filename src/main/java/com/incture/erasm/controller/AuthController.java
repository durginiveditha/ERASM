package com.incture.erasm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.incture.erasm.dto.request.LoginRequestDto;
import com.incture.erasm.dto.response.LoginResponseDto;
import com.incture.erasm.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(
            @RequestBody LoginRequestDto requestDto) {

        return ResponseEntity.ok(authService.login(requestDto));
    }
}