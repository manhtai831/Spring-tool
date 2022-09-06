package com.example.spring_demo_app.gate;

import com.example.spring_demo_app.common.HeaderStored;
import com.example.spring_demo_app.controllers.AccountController;
import com.example.spring_demo_app.controllers.LuckyController;
import com.example.spring_demo_app.controllers.MktController;
import com.example.spring_demo_app.data.model.AccountModel;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        List<AccountModel> accountModels = new ArrayList<>();
        accountModels.add(new AccountModel("84943574556", "Khongcho1"));
        accountModels.add(new AccountModel("84973589126", "Khongcho1"));
        accountModels.add(new AccountModel("84378041531", "heocon"));

        for (AccountModel accountModel : accountModels) {
            try {
                accountController.shopeeLogin(accountModel.getPhone(), accountModel.getPassword());

                mktController.shopeeCollectCoin();

                System.out.println(new Date() + " Coin collected.\nhttps://shopee-tool.herokuapp.com/api/v1/mkt/collect-coin\n");
            } catch (Exception e) {
                System.out.println(new Date() + " Coin not collected.\nhttps://shopee-tool.herokuapp.com/api/v1/mkt/collect-coin\n");
            }
        }



    }

    @Scheduled(cron = "0 0 9,15 * * *", zone = "GMT+7:00")
    public void luckyNumber() throws IOException {
        List<AccountModel> accountModels = new ArrayList<>();
        accountModels.add(new AccountModel("84943574556", "Khongcho1"));
        accountModels.add(new AccountModel("84973589126", "Khongcho1"));
        accountModels.add(new AccountModel("84378041531", "heocon"));


        for (AccountModel accountModel : accountModels) {
            try {
                accountController.shopeeLogin(accountModel.getPhone(), accountModel.getPassword());

                HeaderStored.getInstance().removeHeader("origin");

                luckyController.getLuckyInfo();

                luckyController.pickLuckyNumber();

                if (accountModel.getPhone().equals("84943574556")) {
                    luckyController.createLuckyGroup();

                    luckyController.createLinkLuckyGroup("");

                }

                luckyController.getGroupInfoByRel("");

                luckyController.joinGroup("");

                System.out.println(new Date() + " Coin collected.\nhttps://shopee-tool.herokuapp.com/api/v1/mkt/collect-coin\n");
            } catch (Exception e) {
                System.out.println(new Date() + " Coin not collected");
            }

        }
    }
    @Scheduled(cron = "0 0/30 * * * *", zone = "GMT+7:00")
    public void pingToServer() throws IOException {

        Request request = new Request.Builder().url("https://shopee-tool.herokuapp.com/ping").build();
        Response response = client.newCall(request).execute();

        System.out.println(new Date() + " was pinged.\nhttps://shopee-tool.herokuapp.com/ping\n" + response.body().string());
    }
}
