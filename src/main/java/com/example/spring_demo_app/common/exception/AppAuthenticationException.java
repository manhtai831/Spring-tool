package com.example.spring_demo_app.common.exception;


import org.springframework.security.core.AuthenticationException;

import javax.servlet.ServletException;

public class AppAuthenticationException extends ServletException {
    public AppAuthenticationException(String message) {
        super(message);
    }
}
