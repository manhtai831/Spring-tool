package com.example.spring_demo_app.common.exception;

import org.springframework.security.core.AuthenticationException;

public class LoginException extends AuthenticationException {
    public LoginException(String message) {
        super(message);
    }
}
