package com.example.spring_demo_app.controllers;

import com.example.spring_demo_app.common.AccountManager;
import com.example.spring_demo_app.common.model.BaseResponse;
import com.example.spring_demo_app.data.model.AccountModel;
import com.example.spring_demo_app.data.services.AccountService;
import com.example.spring_demo_app.data.services.MktService;
import com.example.spring_demo_app.domain.service.AccountServiceImpl;
import com.example.spring_demo_app.domain.service.MktServiceImpl;
import com.example.spring_demo_app.domain.service.UserServiceImpl;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@RestController
@RequestMapping("api/v1/mkt")
public class MktController {
    private final MktService mktService;
    private final AccountService accountService;

    @Autowired
    public MktController(UserServiceImpl userService, MktServiceImpl mktService, AccountServiceImpl accountService) {
        this.mktService = mktService;
        this.accountService = accountService;
    }

    @GetMapping("/collect-coin")
    public BaseResponse shopeeCollectCoin() throws IOException {

        Response responseCoin = mktService.collectCoin();

        return BaseResponse.success(responseCoin.body().string());
    }

    @GetMapping("/all")
    public BaseResponse all() throws IOException, NoSuchAlgorithmException {

        for (AccountModel accountModel : AccountManager.getInstance().getAccounts()) {


            accountService.login(accountModel.getPhone(), accountModel.getPassword(), accountModel.getSpcF());

            shopeeCollectCoin();

            System.out.println(new Date() + " Coin collected.\nhttps://shopee-tool.herokuapp.com/api/v1/mkt/collect-coin\n");

        }
        return BaseResponse.success("OK");
    }
}
