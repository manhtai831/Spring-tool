package com.example.spring_demo_app.controllers;

import com.example.spring_demo_app.data.services.LuckyService;
import com.example.spring_demo_app.domain.service.LuckyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/lucky")
public class LuckyController {

    private final LuckyService luckyService;

    @Autowired
    public LuckyController(LuckyServiceImpl luckyService) {
        this.luckyService = luckyService;
    }
}
