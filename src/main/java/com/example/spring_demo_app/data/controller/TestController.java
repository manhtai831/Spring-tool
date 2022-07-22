package com.example.spring_demo_app.data.controller;

import com.example.spring_demo_app.common.model.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ping")
public class TestController {

    @GetMapping()
    BaseResponse ping(){
        return  BaseResponse.success("Ping success");
    }
}
