package com.example.spring_demo_app.gate;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AppSchedule {

//    @Scheduled(cron = "*/5 * * * * *")
//    public void getCoinDaily() {
//        System.out.println(new Date());
//        System.out.println("Run on 10 seconds per time");
//    }

    @Scheduled(cron = "* * */3 * * *")
    public void pingToServer() {
        System.out.println(new Date() + "was pinged");
    }
}
