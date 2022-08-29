package com.example.spring_demo_app.domain.service;

import com.example.spring_demo_app.common.HeaderStored;
import com.example.spring_demo_app.data.services.MktService;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MktServiceImpl implements MktService {

    private final OkHttpClient client;

    @Autowired
    public MktServiceImpl(OkHttpClient client) {
        this.client = client;
    }

    @Override
    public Response collectCoin() throws IOException {
        RequestBody bodyCoin = RequestBody.create("{}", HeaderStored.MEDIA_TYPE);

        Request requestCoin = new Request.Builder().url("https://shopee.vn/mkt/coins/api/v2/checkin_new").headers(Headers.of(HeaderStored.getInstance().headers)).post(bodyCoin).build();

        //        System.out.println(responseCoin.body().string());
        return client.newCall(requestCoin).execute();
    }
}
