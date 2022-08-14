package com.example.spring_demo_app.gate;

import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class AppSchedule {

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");

    OkHttpClient client;

    @Autowired
    public AppSchedule(OkHttpClient client) {
        this.client = client;
    }

    @Scheduled(cron = "0 0/30 6 * * *")
    public void getCoinDaily() throws IOException {
        String json = "{\n" +
                "    \"phone\": \"84943574556\",\n" +
                "    \"password\": \"674baeebefa807fb3da8e561e4746d168d9b5317621f7dd810148649e833c339\",\n" +
                "    \"support_ivs\": true,\n" +
                "    \"client_identifier\": {\n" +
                "        \"security_device_fingerprint\": \"JXRorODsKd5nHpnBfcBysw==|XpX6gTpIwn8lxBhl8wcpAe1xagdRy/sydPgURvM9QtE3yi41MyktKi8Nv5nDZdDXihcu98XVNVWUOn0A+XONM5E=|I9JsoazDTEmX1bh5|04|3\"\n" +
                "    }\n" +
                "}";

        RequestBody body = RequestBody.create(json, MEDIA_TYPE);

        Request request = new Request.Builder().url("https://shopee.vn/api/v4/account/login_by_password").post(body).build();

        Response response = client.newCall(request).execute();
        StringBuilder cookies = new StringBuilder();
        response.headers("set-cookie").forEach(element -> {
            String s = element.split(";")[0];
            cookies.append(s).append("; ");
        });
        System.out.println(cookies.toString());

        String cookieAuth = "SPC_F=gY2N4BOQiNOVGnnu9oFW6NpJFgfoeS6k; csrftoken=aWKV70orJMVv1Rl7T2xoDUx7X0Rhxvii";

        cookies.append(cookieAuth);

        Map<String, String> headers = new HashMap<>();
        headers.put("cookie", cookies.toString());
        headers.put("referer", "https://shopee.vn/buyer/login?next=https://shopee.vn/");
        headers.put("origin", "https://shopee.vn");
        headers.put("x-csrftoken", "aWKV70orJMVv1Rl7T2xoDUx7X0Rhxvii");
        headers.put("x-api-source", "pc");

        RequestBody bodyCoin = RequestBody.create("{}", MEDIA_TYPE);

        Request requestCoin = new Request.Builder().url("https://shopee.vn/mkt/coins/api/v2/checkin_new").headers(Headers.of(headers)).post(bodyCoin).build();

        Response responseCoin = client.newCall(requestCoin).execute();
        System.out.println(responseCoin.body().string());



    }

    @Scheduled(cron = "* * */3 * * *")
    public void pingToServer() throws IOException {

        Request request = new Request.Builder().url("https://shopee-tool.herokuapp.com/ping").build();
        Response response = client.newCall(request).execute();

        System.out.println(new Date() + "was pinged.\nhttps://shopee-tool.herokuapp.com/ping\n" + response.body().string());
    }
}
