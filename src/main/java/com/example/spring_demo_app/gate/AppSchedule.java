package com.example.spring_demo_app.gate;

import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class AppSchedule {



    OkHttpClient client;

    @Autowired
    public AppSchedule(OkHttpClient client) {
        this.client = client;
    }

    @Scheduled(cron = "0 0/30 6 * * *",zone = "GMT+7:00")
    public void getCoinDaily() throws IOException {

        Request request = new Request.Builder().url("https://shopee-tool.herokuapp.com/api/v1/mkt/collect-coin").build();
        Response response = client.newCall(request).execute();

        System.out.println(new Date() + " Coin collected.\nhttps://shopee-tool.herokuapp.com/api/v1/mkt/collect-coin\n" + response.body().string());
    }

    @Scheduled(cron = "0 0/5 * * * *",zone = "GMT+7:00")
    public void pingToServer() throws IOException {

        Request request = new Request.Builder().url("https://shopee-tool.herokuapp.com/ping").build();
        Response response = client.newCall(request).execute();

        System.out.println(new Date() + " was pinged.\nhttps://shopee-tool.herokuapp.com/ping\n" + response.body().string());
    }
}
