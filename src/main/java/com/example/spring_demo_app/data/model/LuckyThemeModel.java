package com.example.spring_demo_app.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LuckyThemeModel {
    public boolean phone_verified;
    public boolean is_authenticated;
    public Assets assets;
    public Session session;
    public OnboardingInfo onboarding_info;
    public boolean is_picking_streak_active;
    public String wss_token;
    public String user_id;
}


