package com.example.spring_demo_app.common.system_config;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Client {

    @Bean
    OkHttpClient getClient() {
        return new OkHttpClient();
    }
}
