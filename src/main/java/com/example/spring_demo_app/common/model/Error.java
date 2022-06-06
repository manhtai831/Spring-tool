package com.example.spring_demo_app.common.model;

import lombok.Data;

@Data
public class Error {
    private int code;
    private String message;


    public static Error success() {
        return new Error(0, "Thành công");
    }

    public static Error error() {
        return new Error(99, "Đã có lỗi xảy ra");
    }

    public static Error custom(int code, String msg) {
        return new Error(code, msg);
    }

    public Error(int code, String message) {
        this.message = message;
        this.code = code;
    }

}
