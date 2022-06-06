package com.example.spring_demo_app.common.exception;

import com.example.spring_demo_app.common.contants.AppErrorCode;
import com.example.spring_demo_app.common.model.BaseResponse;
import com.example.spring_demo_app.common.model.Error;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SystemExceptionController {


    @ExceptionHandler({AuthenticationException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public BaseResponse handleAuthenticationException(AuthenticationException e) {
        e.printStackTrace();
        return BaseResponse.error(Error.custom(AppErrorCode.UNAUTHORIZED, "Unauthorized"));
    }

    @ExceptionHandler({AppException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public BaseResponse handleExistException(AppException e) {
        return BaseResponse.error(Error.custom(AppErrorCode.NOT_FOUND, e.getMessage()));
    }


    @ExceptionHandler({BindException.class})
    public BaseResponse handleArgumentNotValidException(BindException e) {
        e.printStackTrace();
        String err = "Đã có lỗi xảy ra";
        if (e.getFieldError() != null) err = e.getFieldError().getField() + " " + e.getFieldError().getDefaultMessage();
        return BaseResponse.error(err, Error.custom(AppErrorCode.UNKNOWN, "Đã có lỗi xảy ra"));
    }

    @ExceptionHandler({Exception.class})
    public BaseResponse handleRuntimeException(Exception e) {
        e.printStackTrace();
        return BaseResponse.error(Error.custom(AppErrorCode.UNKNOWN, "Đã có lỗi xảy ra"));
    }
}
