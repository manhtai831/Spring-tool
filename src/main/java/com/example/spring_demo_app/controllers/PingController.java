package com.example.spring_demo_app.controllers;

import com.example.spring_demo_app.common.model.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ping")
public class PingController {

    @GetMapping()
    BaseResponse ping(){
        return  BaseResponse.success("Ping success");
    }
}
