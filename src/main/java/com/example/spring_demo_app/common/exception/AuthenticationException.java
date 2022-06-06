package com.example.spring_demo_app.common.exception;

public class AuthenticationException extends  Exception{
    public AuthenticationException(String message) {
        super(message);
    }
}
