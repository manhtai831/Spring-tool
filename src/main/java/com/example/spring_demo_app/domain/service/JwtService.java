package com.example.spring_demo_app.domain.service;


import org.springframework.security.core.Authentication;

public interface JwtService {
    static final String SECRET_KEY = "123456789";
    static final long EXPIRE_TIME = 20730L;

    String generateTokenLogin(Authentication authentication);

    Long getIdFromJwtToken(String token);

    boolean validateJwtToken(String authToken);
}
