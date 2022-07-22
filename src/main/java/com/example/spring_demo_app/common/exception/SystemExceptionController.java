package com.example.spring_demo_app.common.exception;

import com.example.spring_demo_app.common.contants.AppConstants;
import com.example.spring_demo_app.common.model.BaseResponse;
import com.example.spring_demo_app.common.model.Error;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SystemExceptionController {


    @ExceptionHandler({AppAuthenticationException.class})
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse handleAuthenticationException(AppAuthenticationException e) {
        e.printStackTrace();
        return BaseResponse.error(Error.custom(AppConstants.Code.UNAUTHORIZED, "Unauthorized"));
    }

    @ExceptionHandler({NotFoundException.class})
    public BaseResponse handleExistException(NotFoundException e) {
        return BaseResponse.error(Error.custom(AppConstants.Code.NOT_FOUND, e.getMessage()));
    }


    @ExceptionHandler({LoginException.class})
    public BaseResponse handleExistException(LoginException e) {
        return BaseResponse.error(Error.custom(AppConstants.Code.ERROR_AUTHENTICATION, e.getMessage()));
    }


    @ExceptionHandler({BindException.class})
    public BaseResponse handleArgumentNotValidException(BindException e) {
        e.printStackTrace();
        String err = "Đã có lỗi xảy ra";
        if (e.getFieldError() != null) err = e.getFieldError().getField() + " " + e.getFieldError().getDefaultMessage();
        return BaseResponse.error(err, Error.custom(AppConstants.Code.UNKNOWN, "Đã có lỗi xảy ra"));
    }

    @ExceptionHandler({Exception.class})
    public BaseResponse handleRuntimeException(Exception e) {
        e.printStackTrace();
        return BaseResponse.error(Error.custom(AppConstants.Code.UNKNOWN, "Đã có lỗi xảy ra"));
    }
}
