package com.example.spring_demo_app.common.exception;


import org.springframework.security.core.AuthenticationException;

public class AppAuthenticationException extends AuthenticationException {
    public AppAuthenticationException(String message) {
        super(message);
    }
}
