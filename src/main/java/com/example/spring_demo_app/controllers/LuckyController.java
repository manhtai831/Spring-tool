package com.example.spring_demo_app.controllers;

import com.example.spring_demo_app.common.AccountManager;
import com.example.spring_demo_app.common.HeaderStored;
import com.example.spring_demo_app.common.model.BaseResponse;
import com.example.spring_demo_app.data.model.AccountModel;
import com.example.spring_demo_app.data.model.GateModel;
import com.example.spring_demo_app.data.model.LuckyGroupSession;
import com.example.spring_demo_app.data.services.AccountService;
import com.example.spring_demo_app.data.services.LuckyService;
import com.example.spring_demo_app.domain.service.LuckyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/lucky")
public class LuckyController {

    private final LuckyService luckyService;
    private final AccountController accountService;

    @Autowired
    public LuckyController(LuckyServiceImpl luckyService, AccountController accountService) {
        this.luckyService = luckyService;
        this.accountService = accountService;
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
        GateModel gateModel = luckyService.createLuckyGroup();
        return BaseResponse.success(gateModel);
    }

    @GetMapping("/create_link")
    public BaseResponse createLinkLuckyGroup(@Param("") String themeId) throws IOException {
        String rel = luckyService.createLinkLuckyGroup(themeId);
        return BaseResponse.success(rel);
    }

    @GetMapping("/group-by-rel")
    public BaseResponse getGroupInfoByRel(String rel) throws IOException {
        LuckyGroupSession session = luckyService.getGroupInfoByRel(rel);
        return BaseResponse.success(session);
    }

    @GetMapping("/join-group")
    public BaseResponse joinGroup(String groupId) throws IOException {
        GateModel gateModel = luckyService.joinGroup(groupId);
        return BaseResponse.success(gateModel);
    }

    @GetMapping("/all")
    public BaseResponse all() throws IOException {

        for (AccountModel accountModel : AccountManager.getInstance().getAccounts()) {
            try {
                accountService.shopeeLogin(accountModel.getPhone(), accountModel.getPassword());

                HeaderStored.getInstance().removeHeader("origin");

                luckyService.getLuckyInfo();

                luckyService.pickLuckyNumber();

                luckyService.claimReward();

                if (accountModel.getIsLeader()) {
                    luckyService.createLuckyGroup();

                    luckyService.createLinkLuckyGroup("");

                }

                luckyService.getGroupInfoByRel("");

                luckyService.joinGroup("");

                System.out.println(new Date() + " Coin collected.\nhttps://shopee-tool.herokuapp.com/api/v1/mkt/collect-coin\n");
            } catch (Exception e) {
                System.out.println(new Date() + " Coin not collected");
            }

        }
        return BaseResponse.success("gateModel");
    }
}
