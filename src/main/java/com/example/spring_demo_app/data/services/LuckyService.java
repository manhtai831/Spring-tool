package com.example.spring_demo_app.data.services;

import com.example.spring_demo_app.data.model.LuckyThemeModel;

public interface LuckyService {

    LuckyThemeModel getLuckyInfo();

    void pickLuckyNumber();

    void claimReward();

    String createLuckyGroup(); // Trả về groupId

    void joinGroup(String groupId);

}
