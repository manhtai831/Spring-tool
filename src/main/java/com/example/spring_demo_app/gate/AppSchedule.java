package com.example.spring_demo_app.gate;

import com.example.spring_demo_app.controllers.AccountController;
import com.example.spring_demo_app.controllers.LuckyController;
import com.example.spring_demo_app.controllers.MktController;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Component
public class AppSchedule {

    private final OkHttpClient client;
    private final MktController mktController;
    private final LuckyController luckyController;


    @Autowired
    public AppSchedule(OkHttpClient client, MktController mktController, LuckyController luckyController) {
        this.client = client;
        this.mktController = mktController;
        this.luckyController = luckyController;
    }


    @Scheduled(cron = "0 30 6 * * *", zone = "GMT+7:00")
    public void getCoinDaily() throws IOException, NoSuchAlgorithmException {
        mktController.all();
    }

    @Scheduled(cron = "0 0 9,15 * * *", zone = "GMT+7:00")
    public void luckyNumber() throws IOException, NoSuchAlgorithmException {
        luckyController.all();
    }

    @Scheduled(cron = "0 0/30 * * * *", zone = "GMT+7:00")
    public void pingToServer() throws IOException {

        Request request = new Request.Builder().url("https://shopee-tool.herokuapp.com/ping").build();
        Response response = client.newCall(request).execute();

        System.out.println(new Date() + " was pinged.\nhttps://shopee-tool.herokuapp.com/ping\n" + response.body().string());
    }
}
