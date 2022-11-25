package com.example.spring_demo_app.data.services;



public interface JwtService {
    static final String SECRET_KEY = "123456789";
    static final long EXPIRE_TIME = 20730L;

    String generateTokenLogin(Long id);

    Long getIdFromJwtToken(String token);

    boolean validateJwtToken(String authToken);
}
