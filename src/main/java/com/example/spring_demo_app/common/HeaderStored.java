package com.example.spring_demo_app.common;

import okhttp3.MediaType;
import okhttp3.Response;

import java.util.HashMap;
import java.util.Map;

public class HeaderStored {
    static HeaderStored headerStored;
    public Map<String, String> headers = new HashMap<>();

    public static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");

    public static HeaderStored getInstance() {
        if (headerStored == null) headerStored = new HeaderStored();
        return headerStored;
    }

    public void setHeader(Response response) {
        StringBuilder cookies = new StringBuilder();
        response.headers("set-cookie").forEach(element -> {
            String s = element.split(";")[0];
            cookies.append(s).append("; ");
        });
//        System.out.println(cookies.toString());

        String cookieAuth = "SPC_F=gY2N4BOQiNOVGnnu9oFW6NpJFgfoeS6k; csrftoken=aWKV70orJMVv1Rl7T2xoDUx7X0Rhxvii";

        cookies.append(cookieAuth);


        headers.put("cookie", cookies.toString());
        headers.put("referer", "https://shopee.vn/buyer/login?next=https://shopee.vn/");
        headers.put("origin", "https://shopee.vn");
        headers.put("x-csrftoken", "aWKV70orJMVv1Rl7T2xoDUx7X0Rhxvii");
        headers.put("x-api-source", "pc");
    }

    public void addHeader(String key, String value) {

        headers.put(key, value);
    }

    public void addHeaders(Map<String, String> headers) {

        headers.putAll(headers);
    }

    public void clear() {

        headers.clear();
    }


}
