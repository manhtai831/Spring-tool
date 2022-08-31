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

    public void setHeader(/*Response response*/) {
        StringBuilder cookies = new StringBuilder();
//        response.headers("set-cookie").forEach(element -> {
//            String s = element.split(";")[0];
//            cookies.append(s).append("; ");
//        });
//        System.out.println(cookies.toString());

        String cookieAuth = "SPC_F=gY2N4BOQiNOVGnnu9oFW6NpJFgfoeS6k; csrftoken=aWKV70orJMVv1Rl7T2xoDUx7X0Rhxvii";

        cookies.append(cookieAuth);


//        headers.put("cookie", cookies.toString());
        headers.put("cookie", "REC_T_ID=1c3fa18d-28ce-11ed-9b90-9440c93d3b0c; SPC_CLIENTID=Z1kyTjRCT1FpTk9Wglcrqtozebobrjlo; SPC_EC=OTZwcTliSTdKUDJYMTlQbaKXSeImAr96lbwZR+tF9SXz6d2XkmeTmWc92LtNUM1Wz2uy+NL+XXr0rl09TG63Pg+zfi+MTzKVw3bzWzT3Tc23FwBiJFQOhn2zfSANc0EkvCrgV3tHGa03kAcTZOLxRMNjPK/NYOr4VWgYfBgBJUA=; SPC_F=RuWjg123Ogj6EGEGYNeemdCI2sjiQKLW; SPC_R_T_ID=bMSyqbHeIfqQNhwHkSY/PSa++xRLiuidhifGKYbXRQMW9k6hZNLOl9wmefe1IW3Lf9qfo4XkxbRwpEnTzooARW3zCDbVfrGXsPNWtaxq3rgSjvjeS663Gegpk9bIR9YdjVuIsDsqhoHWPMpgLu8g0daw2HLs8Eb6KqBAfXK4lQ4=; SPC_R_T_IV=SjBRbFJvNUxMdVRJTE9MTA==; SPC_SI=wfwFYwAAAAA4QmgyaUtTdCkuhQAAAAAATXI4WnU4Tmc=; SPC_ST=.VlVTcnAweWdTUVBJdHhlRCbCR8AnINVeW9yOQmtMzGDPljeRhAgcw2wOIUu1K0jSICRa+vX9HwJj6ZnTC2Br9FSRtiw8ztiLgxFW0hhNDc7WhMbpibpLe2kEIQ/3LT9LFDdoyAv4YphjttTOpXtlq7YB+m5xpasa2ohGCYKala0XUjgDjAHSid4isk/ZqfAmZP0Gha4WEg1kzSR8D421jw==; SPC_T_ID=bMSyqbHeIfqQNhwHkSY/PSa++xRLiuidhifGKYbXRQMW9k6hZNLOl9wmefe1IW3Lf9qfo4XkxbRwpEnTzooARW3zCDbVfrGXsPNWtaxq3rgSjvjeS663Gegpk9bIR9YdjVuIsDsqhoHWPMpgLu8g0daw2HLs8Eb6KqBAfXK4lQ4=; SPC_T_IV=SjBRbFJvNUxMdVRJTE9MTA==");
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
