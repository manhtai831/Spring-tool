package com.example.spring_demo_app.data.controller;

import com.example.spring_demo_app.common.model.BaseResponse;
import com.example.spring_demo_app.common.model.Error;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/**")
public class UnknownController {

    @GetMapping()
    private BaseResponse getUnknown(HttpServletRequest request) {
        return BaseResponse.error(request.getRequestURI() + " not found", Error.error());
    }

    @PostMapping()
    private BaseResponse postUnknown(HttpServletRequest request) {
        return BaseResponse.error(request.getRequestURI() + " not found", Error.error());
    }

    @PutMapping()
    private BaseResponse putUnknown(HttpServletRequest request) {
        return BaseResponse.error(request.getRequestURI() + " not found", Error.error());
    }

    @DeleteMapping()
    private BaseResponse deleteUnknown(HttpServletRequest request) {
        return BaseResponse.error(request.getRequestURI() + " not found", Error.error());
    }

}
