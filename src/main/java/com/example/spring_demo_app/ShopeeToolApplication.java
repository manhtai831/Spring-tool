package com.example.spring_demo_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ShopeeToolApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopeeToolApplication.class, args);

    }

}
