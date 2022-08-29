package com.example.spring_demo_app.controllers;

import com.example.spring_demo_app.common.HeaderStored;
import com.example.spring_demo_app.common.model.BaseResponse;
import com.example.spring_demo_app.domain.service.UserServiceImpl;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/account")
public class AccountController {
    private final UserServiceImpl userService;

    @Autowired
    public AccountController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public BaseResponse shopeeLogin() throws IOException {
        String json = "{\n" +
                "    \"phone\": \"84943574556\",\n" +
                "    \"password\": \"674baeebefa807fb3da8e561e4746d168d9b5317621f7dd810148649e833c339\",\n" +
                "    \"support_ivs\": true,\n" +
                "    \"client_identifier\": {\n" +
                "        \"security_device_fingerprint\": \"JXRorODsKd5nHpnBfcBysw==|XpX6gTpIwn8lxBhl8wcpAe1xagdRy/sydPgURvM9QtE3yi41MyktKi8Nv5nDZdDXihcu98XVNVWUOn0A+XONM5E=|I9JsoazDTEmX1bh5|04|3\"\n" +
                "    }\n" +
                "}";

        Response response = userService.login(json);
        HeaderStored.getInstance().setHeader(response);
        return BaseResponse.success(HeaderStored.getInstance().headers);
    }

}
