package com.example.spring_demo_app.controllers;

import com.example.spring_demo_app.common.HeaderStored;
import com.example.spring_demo_app.common.logging.GsonParserUtils;
import com.example.spring_demo_app.common.model.BaseResponse;
import com.example.spring_demo_app.common.model.Error;
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

@RestController
@RequestMapping("api/v1/account")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }


    @GetMapping("/login")
    public BaseResponse shopeeLogin() throws IOException {
        AccountModel accountModel = new AccountModel("84943574556", "674baeebefa807fb3da8e561e4746d168d9b5317621f7dd810148649e833c339");

        Response response = accountService.login(GsonParserUtils.parseObjectToString(accountModel));

        String responseBody = response.body().string();

        GateModel gateModel = GsonParserUtils.parseStringToObject(responseBody, GateModel.class);

        assert gateModel != null;

        if (gateModel.getError() != 0)
            return BaseResponse.error(Error.custom(1, "Đăng nhập hệ thống shopee không thành công"));

        AccountModel account = GsonParserUtils.parseStringToObject(gateModel.getData().toString(), AccountModel.class);

        assert account != null;

        accountModel.setUserid(account.getUserid());

        accountService.saveAccount(accountModel);


        HeaderStored.getInstance().clear();

        HeaderStored.getInstance().setHeader(response);

        HeaderStored.getInstance().addHeader("SPC_U", account.getUserid().toString());

        return BaseResponse.success(HeaderStored.getInstance().headers);
    }

}
