package com.example.spring_demo_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class ShopeeToolApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(ShopeeToolApplication.class, args);
        List<Integer> integers = new ArrayList<>();
        integers.add(0);
        integers.add(1);
        integers.add(2);

        for(Integer i : integers){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
        }
        integers.forEach(integer -> {

        });
    }

     void f1() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println(new Date() + "Done F1");
    }

     void f2() throws InterruptedException {
        System.out.println(new Date() + "Done F2");
    }
}
