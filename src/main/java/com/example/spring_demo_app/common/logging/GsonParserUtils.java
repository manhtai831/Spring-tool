package com.example.spring_demo_app.common.logging;


import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class GsonParserUtils {
    public static String parseObjectToString(Object object) {
        return new Gson().toJson(object);
    }

    public static <T> T parseStringToObject(String json, Class<T> classObject) {

            return new Gson().fromJson(json, classObject);
//            try {    } catch (Exception e) {
//            return null;
//        }
    }
    public static <T> T parseStringToObject(JsonReader json, Class<T> classObject) {

            return new Gson().fromJson(json, classObject);
//            try {    } catch (Exception e) {
//            return null;
//        }
    }
}