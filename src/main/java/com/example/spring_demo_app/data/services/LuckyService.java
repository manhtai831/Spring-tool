package com.example.spring_demo_app.data.services;

import com.example.spring_demo_app.data.model.GateModel;
import com.example.spring_demo_app.data.model.LuckyGroupSession;

import java.io.IOException;

public interface LuckyService {

    GateModel getLuckyInfo() throws IOException;

    GateModel pickLuckyNumber() throws IOException;

    GateModel claimReward() throws IOException;

    GateModel createLuckyGroup() throws IOException;

    String createLinkLuckyGroup(String themeID) throws IOException;

    LuckyGroupSession getGroupInfoByRel(String rel) throws IOException;

    GateModel joinGroup(String groupId) throws IOException;

}
