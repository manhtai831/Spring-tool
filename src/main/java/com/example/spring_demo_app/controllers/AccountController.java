package com.example.spring_demo_app.controllers;

import com.example.spring_demo_app.common.model.BaseResponse;
import com.example.spring_demo_app.common.utils.HashSecurity;
import com.example.spring_demo_app.data.model.AccountModel;
import com.example.spring_demo_app.data.services.AccountService;
import com.example.spring_demo_app.domain.service.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("api/v1/account")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }


    @GetMapping("/login")
    public BaseResponse shopeeLogin(@RequestParam() String userName,@RequestParam() String password,@RequestParam() String spcF) throws IOException, NoSuchAlgorithmException {
        AccountModel accountModel = accountService.login(userName, password,  spcF);

        return BaseResponse.success(accountModel.toJson());
    }

}
