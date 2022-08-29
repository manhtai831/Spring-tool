package com.example.spring_demo_app.data.services;

import okhttp3.Response;

import java.io.IOException;

public interface MktService {
    Response collectCoin() throws IOException;
}
