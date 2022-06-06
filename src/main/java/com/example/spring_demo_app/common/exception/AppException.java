package com.example.spring_demo_app.common.exception;

public class AppException extends Exception {

    public AppException(String message) {
        super(message);
    }

    public static AppException msg(String msg) {
        return new AppException(msg);
    }

    public static AppException notExist(Object msg) {
        return new AppException(msg.toString() + " không tồn tại.");
    }
}
