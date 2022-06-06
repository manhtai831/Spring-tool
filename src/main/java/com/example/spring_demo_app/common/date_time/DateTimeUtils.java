package com.example.spring_demo_app.common.date_time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtils {

    public static final String P0 = "dd/MM/yyyy HH:mm:ss";


    public static String convertTo(String pattern, Date date) {
        return new SimpleDateFormat(pattern).format(date);
    }

    public static Date toDate(String pattern, String date) {
        try {
            return new SimpleDateFormat(pattern).parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String toNewPattern(String fromPattern, String toPattern, String date) {
        try {
            return new SimpleDateFormat(toPattern).format(new SimpleDateFormat(fromPattern).parse(date));
        } catch (ParseException e) {
            return null;
        }
    }

}
