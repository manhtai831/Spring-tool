package com.example.spring_demo_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class SpringDemoAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDemoAppApplication.class, args);
    }

}
