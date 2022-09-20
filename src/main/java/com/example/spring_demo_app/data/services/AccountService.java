package com.example.spring_demo_app.data.services;

import com.example.spring_demo_app.data.model.AccountModel;
import okhttp3.Response;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface AccountService {
    void saveAccount(AccountModel accountModel);

    void updateAccount(AccountModel accountModel);

    AccountModel login(String userName,String password, String spcF) throws IOException, NoSuchAlgorithmException;
}
