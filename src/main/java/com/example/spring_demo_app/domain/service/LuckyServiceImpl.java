package com.example.spring_demo_app.domain.service;

import com.example.spring_demo_app.common.GroupStored;
import com.example.spring_demo_app.common.HeaderStored;
import com.example.spring_demo_app.common.contants.ShopeeConstants;
import com.example.spring_demo_app.common.logging.GsonParserUtils;
import com.example.spring_demo_app.common.utils.RandomString;
import com.example.spring_demo_app.data.model.GateModel;
import com.example.spring_demo_app.data.model.LuckyGroupSession;
import com.example.spring_demo_app.data.model.LuckyThemeModel;
import com.example.spring_demo_app.data.services.LuckyService;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class LuckyServiceImpl implements LuckyService {
    private final OkHttpClient client;

    @Autowired
    public LuckyServiceImpl(OkHttpClient client) {
        this.client = client;
    }

    @Override
    public GateModel getLuckyInfo() throws IOException {
        String json = GsonParserUtils.parseObjectToString(new LuckyThemeModel.Builder()
                .setThemeType("number_picker_theme")
                .setSource("sogiday")
                .setThemeId("e2beb3258915d83a94d50fc8b9b5393b")
                .build());
        RequestBody bodyCoin = RequestBody.create(json, HeaderStored.MEDIA_TYPE);

        Request requestCoin = new Request.Builder().url(ShopeeConstants.ShopeeUrl.SO_GI_DAY + ShopeeConstants.ShopeeLottery.THEME_INFO)
                .headers(Headers.of(HeaderStored.getInstance().getHeaders()))
                .post(bodyCoin)
                .build();
        Response response = client.newCall(requestCoin).execute();

        String responseBody = response.body().string();

        GateModel gateModel = GsonParserUtils.parseStringToObject(responseBody, GateModel.class);

        if (gateModel == null) return null;

        String jsonData = GsonParserUtils.parseObjectToString(gateModel.getData());

        LuckyThemeModel themeModel = GsonParserUtils.parseStringToObject(jsonData, LuckyThemeModel.class);

        GroupStored.getInstance().setLuckyThemeModel(themeModel);
        gateModel.setData(themeModel);
        System.out.println("-------------getLuckyInfo________________" + themeModel.getSession().getSession_id());

        return gateModel;
    }

    @Override
    public GateModel pickLuckyNumber() throws IOException {

        RandomString randomString = new RandomString(GroupStored.getInstance().getLuckyThemeModel().getSession().getNumber_of_digits(), RandomString.digits);
        String json = GsonParserUtils.parseObjectToString(new LuckyThemeModel.Builder()
                .setNumber(randomString.nextString())
                .setSessionId(GroupStored.getInstance().getLuckyThemeModel().getSession().getSession_id())
                .setSource("sogiday")
                .setThemeType("number_picker_theme")
                .build());
        RequestBody bodyCoin = RequestBody.create(json, HeaderStored.MEDIA_TYPE);

        Request requestCoin = new Request.Builder().url(ShopeeConstants.ShopeeUrl.SO_GI_DAY + ShopeeConstants.ShopeeLottery.PICK_NUMBER)
                .headers(Headers.of(HeaderStored.getInstance().getHeaders()))
                .post(bodyCoin)
                .build();
        Response response = client.newCall(requestCoin).execute();

        String body = response.body().string();
        System.out.println("-------------pickLuckyNumber________________" + body);
        return GsonParserUtils.parseStringToObject(body, GateModel.class);

    }

    @Override
    public GateModel claimReward() throws IOException {
        RequestBody bodyCoin = RequestBody.create("{}", HeaderStored.MEDIA_TYPE);

        Request requestCoin = new Request.Builder().url(ShopeeConstants.ShopeeUrl.SO_GI_DAY + ShopeeConstants.ShopeeLottery.CLAIM_REWARD)
                .headers(Headers.of(HeaderStored.getInstance().getHeaders()))
                .post(bodyCoin)
                .build();

        Response response = client.newCall(requestCoin).execute();

        String body = response.body().string();

        System.out.println("-------------CLAIM________________" + body);

        return GsonParserUtils.parseStringToObject(body, GateModel.class);
    }

    @Override
    public GateModel createLuckyGroup() throws IOException {

        RequestBody bodyCoin = RequestBody.create("{}", HeaderStored.MEDIA_TYPE);

        Request requestCoin = new Request.Builder().url(ShopeeConstants.ShopeeUrl.SO_GI_DAY + ShopeeConstants.ShopeeLottery.CREATE_GROUP)
                .headers(Headers.of(HeaderStored.getInstance().getHeaders()))
                .post(bodyCoin)
                .build();

        Response response = client.newCall(requestCoin).execute();

        String body = response.body().string();

        System.out.println("-------------createLuckyGroup " + body);

        return GsonParserUtils.parseStringToObject(body, GateModel.class);
    }

    @Override
    public String createLinkLuckyGroup(String themeID) throws IOException {
        String json = GsonParserUtils.parseObjectToString(new LuckyThemeModel.Builder()
                .setThemeId("e2beb3258915d83a94d50fc8b9b5393b")
                .build());

        RequestBody bodyCoin = RequestBody.create(json, HeaderStored.MEDIA_TYPE);

        Request requestCoin = new Request.Builder().url(ShopeeConstants.ShopeeUrl.SO_GI_DAY + ShopeeConstants.ShopeeLottery.CREATE_GROUP_LINK)
                .headers(Headers.of(HeaderStored.getInstance().getHeaders()))
                .post(bodyCoin)
                .build();

        Response response = client.newCall(requestCoin).execute();
        String body = response.body().string();

        GateModel gateModel = GsonParserUtils.parseStringToObject(body, GateModel.class);
        System.out.println("-------------createLinkLuckyGroup_______ " + gateModel);
        if (gateModel == null) return null;

        if (gateModel.getData() == null) return null;

        String jsonData = GsonParserUtils.parseObjectToString(gateModel.getData());

        LuckyThemeModel themeModel = GsonParserUtils.parseStringToObject(jsonData, LuckyThemeModel.class);

        if (themeModel == null) return null;

        GroupStored.getInstance().setRel(themeModel.getRef());

        return themeModel.getRef();
    }

    @Override
    public LuckyGroupSession getGroupInfoByRel(String rel) throws IOException {
        String json = GsonParserUtils.parseObjectToString(new LuckyThemeModel.Builder()
                .setRel(GroupStored.getInstance().getRel())
                .build());

        RequestBody bodyCoin = RequestBody.create(json, HeaderStored.MEDIA_TYPE);

        Request requestCoin = new Request.Builder().url(ShopeeConstants.ShopeeUrl.SO_GI_DAY + ShopeeConstants.ShopeeLottery.GET_GROUP_BY_REL)
                .headers(Headers.of(HeaderStored.getInstance().getHeaders()))
                .post(bodyCoin)
                .build();

        Response response = client.newCall(requestCoin).execute();
        String body = response.body().string();

        GateModel gateModel = GsonParserUtils.parseStringToObject(body, GateModel.class);

        if (gateModel == null) return null;

        if (gateModel.getData() == null) return null;

        String jsonData = GsonParserUtils.parseObjectToString(gateModel.getData());

        LuckyGroupSession luckyGroupSession = GsonParserUtils.parseStringToObject(jsonData, LuckyGroupSession.class);

        if (luckyGroupSession == null) return null;

        GroupStored.getInstance().setGroupSession(luckyGroupSession);

        System.out.println("-------------getGroupInfoByRel__________ " + gateModel);
        return luckyGroupSession;
    }

    @Override
    public GateModel joinGroup(String groupId) throws IOException {
        String json = GsonParserUtils.parseObjectToString(new LuckyThemeModel.Builder()
                .setGroupId(GroupStored.getInstance().getGroupSession().getGroup().getGroup_id())
                .build());
        RequestBody bodyCoin = RequestBody.create(json, HeaderStored.MEDIA_TYPE);

        Request requestCoin = new Request.Builder().url(ShopeeConstants.ShopeeUrl.SO_GI_DAY + ShopeeConstants.ShopeeLottery.JOIN_GROUP)
                .headers(Headers.of(HeaderStored.getInstance().getHeaders()))
                .post(bodyCoin)
                .build();

        Response response = client.newCall(requestCoin).execute();

        String body = response.body().string();

        return GsonParserUtils.parseStringToObject(body, GateModel.class);
    }
}
