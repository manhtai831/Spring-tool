package com.example.spring_demo_app.controllers;

import com.example.spring_demo_app.common.model.BaseResponse;
import com.example.spring_demo_app.data.model.AccountModel;
import com.example.spring_demo_app.data.services.MktService;
import com.example.spring_demo_app.domain.service.MktServiceImpl;
import com.example.spring_demo_app.domain.service.UserServiceImpl;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/mkt")
public class MktController {
    private final MktService mktService;
    private final AccountController accountController;

    @Autowired
    public MktController(UserServiceImpl userService, MktServiceImpl mktService, AccountController accountController) {
        this.mktService = mktService;
        this.accountController = accountController;
    }

    @GetMapping("/collect-coin")
    public BaseResponse shopeeCollectCoin() throws IOException {

        Response responseCoin = mktService.collectCoin();

        return BaseResponse.success(responseCoin.body().string());
    }

    @GetMapping("/all")
    public BaseResponse all() throws IOException {
        List<AccountModel> accountModels = new ArrayList<>();
        accountModels.add(new AccountModel("84943574556", "Khongcho1", true));
        accountModels.add(new AccountModel("84973589126", "Khongcho1"));
        accountModels.add(new AccountModel("84378041531", "heocon"));
        accountModels.add(new AccountModel("84589427969", "123@123aA"));
        for (AccountModel accountModel : accountModels) {


            try {
                accountController.shopeeLogin(accountModel.getPhone(), accountModel.getPassword());

                shopeeCollectCoin();

                System.out.println(new Date() + " Coin collected.\nhttps://shopee-tool.herokuapp.com/api/v1/mkt/collect-coin\n");
            } catch (Exception e) {
                System.out.println(new Date() + " Coin not collected.\nhttps://shopee-tool.herokuapp.com/api/v1/mkt/collect-coin\n");
            }
        }
        return BaseResponse.success("OK");
    }
}
