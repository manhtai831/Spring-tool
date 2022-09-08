package com.example.spring_demo_app.controllers;

import com.example.spring_demo_app.common.HeaderStored;
import com.example.spring_demo_app.common.logging.GsonParserUtils;
import com.example.spring_demo_app.common.model.BaseResponse;
import com.example.spring_demo_app.common.model.Error;
import com.example.spring_demo_app.common.utils.HashSecurity;
import com.example.spring_demo_app.data.model.AccountModel;
import com.example.spring_demo_app.data.model.GateModel;
import com.example.spring_demo_app.data.services.AccountService;
import com.example.spring_demo_app.domain.service.AccountServiceImpl;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public BaseResponse shopeeLogin(String userName,String password) throws IOException, NoSuchAlgorithmException {
//        AccountModel accountModel = new AccountModel("84973589126", HashSecurity.hash(HashSecurity.hash("Khongcho1",HashSecurity.MD5),HashSecurity.SHA256));
        AccountModel accountModel = new AccountModel(userName, HashSecurity.hash(HashSecurity.hash(password,HashSecurity.MD5),HashSecurity.SHA256));

        Response response = accountService.login(GsonParserUtils.parseObjectToString(accountModel));

        String responseBody = response.body().string();

        GateModel gateModel = GsonParserUtils.parseStringToObject(responseBody, GateModel.class);

        assert gateModel != null;

        if (gateModel.getError() != 0)
            return BaseResponse.error(Error.custom(1, "Đăng nhập hệ thống shopee không thành công: " + gateModel));
        String s = GsonParserUtils.parseObjectToString(gateModel.getData());
        AccountModel account = GsonParserUtils.parseStringToObject(s, AccountModel.class);

        assert account != null;

        accountModel.setUserid(account.getUserid());

        accountService.saveAccount(accountModel);


        HeaderStored.getInstance().clear();

        HeaderStored.getInstance().setHeader(response);

        HeaderStored.getInstance().addHeader("cookie", "SPC_U=" + account.getUserid());

        return BaseResponse.success(HeaderStored.getInstance().getHeaders().toString());
    }

}
