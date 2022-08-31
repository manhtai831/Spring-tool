package com.example.spring_demo_app.domain.service;

import com.example.spring_demo_app.common.HeaderStored;
import com.example.spring_demo_app.common.contants.ShopeeConstants;
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

        Request requestCoin = new Request.Builder().url(ShopeeConstants.ShopeeUrl.BASE_URL + ShopeeConstants.ShopeeMkt.COIN)
                .headers(Headers.of(HeaderStored.getInstance().headers))
                .post(bodyCoin)
                .build();

        return client.newCall(requestCoin).execute();
    }
}
