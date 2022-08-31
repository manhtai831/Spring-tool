package com.example.spring_demo_app.data.services;

import com.example.spring_demo_app.data.model.AccountModel;
import okhttp3.Response;

import java.io.IOException;

public interface AccountService {
    void saveAccount(AccountModel accountModel);

    void updateAccount(AccountModel accountModel);

    Response login(String json) throws IOException;
}
