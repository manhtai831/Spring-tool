package com.example.spring_demo_app.gate;

import com.example.spring_demo_app.controllers.AccountController;
import com.example.spring_demo_app.controllers.MktController;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Component
public class AppSchedule {

    private final OkHttpClient client;
    private final AccountController accountController;
    private final MktController mktController;


    @Autowired
    public AppSchedule(OkHttpClient client, AccountController accountController, MktController mktController) {
        this.client = client;
        this.accountController = accountController;
        this.mktController = mktController;
    }


    @Scheduled(cron = "0 0/30 6 * * *", zone = "GMT+7:00")
    public void getCoinDaily() throws IOException {
        accountController.shopeeLogin();

        mktController.shopeeCollectCoin();

        System.out.println(new Date() + " Coin collected.\nhttps://shopee-tool.herokuapp.com/api/v1/mkt/collect-coin\n");
    }

    @Scheduled(cron = "0 0/5 * * * *", zone = "GMT+7:00")
    public void pingToServer() throws IOException {

        Request request = new Request.Builder().url("https://shopee-tool.herokuapp.com/ping").build();
        Response response = client.newCall(request).execute();

        System.out.println(new Date() + " was pinged.\nhttps://shopee-tool.herokuapp.com/ping\n" + response.body().string());
    }
}
