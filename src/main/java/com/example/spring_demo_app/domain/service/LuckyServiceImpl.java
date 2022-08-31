package com.example.spring_demo_app.domain.service;

import com.example.spring_demo_app.common.HeaderStored;
import com.example.spring_demo_app.common.contants.ShopeeConstants;
import com.example.spring_demo_app.data.model.LuckyThemeModel;
import com.example.spring_demo_app.data.services.LuckyService;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LuckyServiceImpl implements LuckyService {
    private final OkHttpClient client;

    @Autowired
    public LuckyServiceImpl(OkHttpClient client) {
        this.client = client;
    }

    @Override
    public LuckyThemeModel getLuckyInfo() {
        RequestBody bodyCoin = RequestBody.create("{}", HeaderStored.MEDIA_TYPE);

        Request requestCoin = new Request.Builder().url(ShopeeConstants.ShopeeUrl.SO_GI_DAY + ShopeeConstants.ShopeeMkt.COIN)
                .headers(Headers.of(HeaderStored.getInstance().headers))
                .post(bodyCoin)
                .build();

        return client.newCall(requestCoin).execute();
    }

    @Override
    public void pickLuckyNumber() {
        RequestBody bodyCoin = RequestBody.create("{}", HeaderStored.MEDIA_TYPE);

        Request requestCoin = new Request.Builder().url(ShopeeConstants.ShopeeUrl.BASE_URL + ShopeeConstants.ShopeeMkt.COIN)
                .headers(Headers.of(HeaderStored.getInstance().headers))
                .post(bodyCoin)
                .build();

        return client.newCall(requestCoin).execute();
    }

    @Override
    public void claimReward() {
        RequestBody bodyCoin = RequestBody.create("{}", HeaderStored.MEDIA_TYPE);

        Request requestCoin = new Request.Builder().url(ShopeeConstants.ShopeeUrl.BASE_URL + ShopeeConstants.ShopeeMkt.COIN)
                .headers(Headers.of(HeaderStored.getInstance().headers))
                .post(bodyCoin)
                .build();

        return client.newCall(requestCoin).execute();
    }

    @Override
    public String createLuckyGroup() {
        return null;
    }

    @Override
    public void joinGroup(String groupId) {
        RequestBody bodyCoin = RequestBody.create("{}", HeaderStored.MEDIA_TYPE);

        Request requestCoin = new Request.Builder().url(ShopeeConstants.ShopeeUrl.BASE_URL + ShopeeConstants.ShopeeMkt.COIN)
                .headers(Headers.of(HeaderStored.getInstance().headers))
                .post(bodyCoin)
                .build();

        return client.newCall(requestCoin).execute();
    }
}
