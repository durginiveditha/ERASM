package com.incture.erasm.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.incture.erasm.dto.request.LoginRequestDto;
import com.incture.erasm.dto.response.LoginResponseDto;
import com.incture.erasm.security.JwtService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AuthService {

	private static final Logger logger =
	        LoggerFactory.getLogger(AuthService.class);
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    
    public AuthService(AuthenticationManager authenticationManager,
                       JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public LoginResponseDto login(LoginRequestDto requestDto) {
    	logger.info("Login attempt for email: {}", requestDto.getEmail());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestDto.getEmail(),
                        requestDto.getPassword()));

        String token = jwtService.generateToken(requestDto.getEmail());
        logger.info("User logged in successfully: {}", requestDto.getEmail());
        return new LoginResponseDto(token);
    }
}