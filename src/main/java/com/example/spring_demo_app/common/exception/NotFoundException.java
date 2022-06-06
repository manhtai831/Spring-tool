package com.example.spring_demo_app.common.exception;

public class NotFoundException extends Exception{
    public NotFoundException(String message) {
        super(message);
    }

    static NotFoundException message(String msg){
        return  new NotFoundException(msg);
    }
}
