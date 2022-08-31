package com.example.spring_demo_app.controllers;

import com.example.spring_demo_app.common.HeaderStored;
import com.example.spring_demo_app.common.model.BaseResponse;
import com.example.spring_demo_app.data.services.MktService;
import com.example.spring_demo_app.data.services.UserService;
import com.example.spring_demo_app.domain.service.MktServiceImpl;
import com.example.spring_demo_app.domain.service.UserServiceImpl;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/mkt")
public class MktController {
    private final MktService mktService;

    @Autowired
    public MktController(UserServiceImpl userService, MktServiceImpl mktService) {
        this.mktService = mktService;
    }

    @GetMapping("/collect-coin")
    public BaseResponse shopeeCollectCoin() throws IOException {

        Response responseCoin  = mktService.collectCoin();

        return BaseResponse.success(responseCoin.body().string());
    }
}
