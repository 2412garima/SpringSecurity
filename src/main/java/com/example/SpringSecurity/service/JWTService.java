package com.example.SpringSecurity.service;

import org.springframework.stereotype.Service;

@Service
public class JWTService {
    public String generateToken(String username) {
        return "dummy-jwt-token-for-" + username; // Placeholder implementation
    }
}
