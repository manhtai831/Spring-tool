package com.example.spring_demo_app.gate;

import com.example.spring_demo_app.common.HeaderStored;
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
import java.util.Date;

@Component
public class AppSchedule {

    private final OkHttpClient client;
    private final AccountController accountController;
    private final MktController mktController;
    private final LuckyController luckyController;


    @Autowired
    public AppSchedule(OkHttpClient client, AccountController accountController, MktController mktController, LuckyController luckyController) {
        this.client = client;
        this.accountController = accountController;
        this.mktController = mktController;
        this.luckyController = luckyController;
    }


    @Scheduled(cron = "0 30 6 * * *", zone = "GMT+7:00")
    public void getCoinDaily() throws IOException {
        accountController.shopeeLogin();

        mktController.shopeeCollectCoin();

        System.out.println(new Date() + " Coin collected.\nhttps://shopee-tool.herokuapp.com/api/v1/mkt/collect-coin\n");
    }

    @Scheduled(cron = "0 30 9,15 * * *", zone = "GMT+7:00")
    public void luckyNumber() throws IOException {
        accountController.shopeeLogin();

        HeaderStored.getInstance().removeHeader("origin");

        luckyController.getLuckyInfo();

        luckyController.pickLuckyNumber();

        luckyController.createLuckyGroup();

        luckyController.createLinkLuckyGroup("");

        luckyController.getGroupInfoByRel("");

        luckyController.joinGroup("");

        System.out.println(new Date() + " Coin collected.\nhttps://shopee-tool.herokuapp.com/api/v1/mkt/collect-coin\n");
    }

//    @Scheduled(cron = "0 10 21 * * *", zone = "GMT+7:00")
//    public void luckyNumberTmp() throws IOException {
//        System.out.println("LOGINNNNNNNNNNNNNNNNN");
//        accountController.shopeeLogin();
//        System.out.println("REMOVE HEADER ORIGINNNNNNNNNNN");
//        HeaderStored.getInstance().removeHeader("origin");
//        System.out.println("luckyController.getLuckyInfo();");
//        luckyController.getLuckyInfo();
//        System.out.println("luckyController.pickLuckyNumber();");
//        luckyController.pickLuckyNumber();
//        System.out.println("luckyController.createLuckyGroup();");
//        luckyController.createLuckyGroup();
//        System.out.println("luckyController.createLinkLuckyGroup();");
//        luckyController.createLinkLuckyGroup("");
//        System.out.println("luckyController.getGroupInfoByRel();");
//        luckyController.getGroupInfoByRel("");
//        System.out.println("luckyController.joinGroup();");
//        luckyController.joinGroup("");
//
//        System.out.println(new Date() + " Coin collected.\nhttps://shopee-tool.herokuapp.com/api/v1/mkt/collect-coin\n");
//    }

    @Scheduled(cron = "0 0/5 * * * *", zone = "GMT+7:00")
    public void pingToServer() throws IOException {

        Request request = new Request.Builder().url("https://shopee-tool.herokuapp.com/ping").build();
        Response response = client.newCall(request).execute();

        System.out.println(new Date() + " was pinged.\nhttps://shopee-tool.herokuapp.com/ping\n" + response.body().string());
    }
}
