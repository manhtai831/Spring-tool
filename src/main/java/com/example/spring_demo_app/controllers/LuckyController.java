package com.example.spring_demo_app.controllers;

import com.example.spring_demo_app.common.model.BaseResponse;
import com.example.spring_demo_app.data.model.GateModel;
import com.example.spring_demo_app.data.model.LuckyGroupSession;
import com.example.spring_demo_app.data.model.LuckyThemeModel;
import com.example.spring_demo_app.data.services.LuckyService;
import com.example.spring_demo_app.domain.service.LuckyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/lucky")
public class LuckyController {

    private final LuckyService luckyService;

    @Autowired
    public LuckyController(LuckyServiceImpl luckyService) {
        this.luckyService = luckyService;
    }

    @GetMapping("/info")
    public BaseResponse getLuckyInfo() throws IOException {
        GateModel gateModel = luckyService.getLuckyInfo();
        return BaseResponse.success(gateModel);
    }

    @GetMapping("/pick")
    public BaseResponse pickLuckyNumber() throws IOException {
       GateModel gateModel = luckyService.pickLuckyNumber();
        return BaseResponse.success(gateModel);
    }

    @GetMapping("/claim")
    public BaseResponse claimReward() throws IOException {
       GateModel gateModel = luckyService.claimReward();
        return BaseResponse.success(gateModel);
    }

    @GetMapping("/create_group")
    public BaseResponse createLuckyGroup() throws IOException {
      GateModel gateModel =  luckyService.createLuckyGroup();
        return BaseResponse.success(gateModel);
    }

    @GetMapping("/create_link")
    public BaseResponse createLinkLuckyGroup(@Param("") String themeId) throws IOException {
        String rel = luckyService.createLinkLuckyGroup(themeId);
        return BaseResponse.success(rel);
    }

    @GetMapping("/group-by-rel")
    public BaseResponse getGroupInfoByRel(String rel) throws IOException {
        LuckyGroupSession session =  luckyService.getGroupInfoByRel(rel);
        return BaseResponse.success(session);
    }

    @GetMapping("/join-group")
    public BaseResponse joinGroup(String groupId) throws IOException {
      GateModel gateModel =  luckyService.joinGroup(groupId);
        return BaseResponse.success(gateModel);
    }
}
