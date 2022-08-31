package com.example.spring_demo_app.common.utils;

import lombok.Setter;

import java.util.Locale;
import java.util.Random;

@Setter
public class RandomString {

    /**
     * Generate a random string.
     */
    public String nextString() {
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbols[random.nextInt(symbols.length)];
        return new String(buf);
    }

    public static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static final String lower = upper.toLowerCase(Locale.ROOT);

    public static final String digits = "0123456789";

    public static final String alphanum = upper + lower + digits;

    private final Random random = new Random();

    private char[] symbols;

    private char[] buf;

    public RandomString(int length, String symbols) {
        if (length < 1) throw new IllegalArgumentException();
        if (symbols.length() < 2) throw new IllegalArgumentException();

        this.symbols = symbols.toCharArray();
        this.buf = new char[length];
    }

    public RandomString() {
    }

    public RandomString(int length) {
        this(length, alphanum);
    }

    public static void main(String[] args) {
        RandomString randomString = new RandomString(6, RandomString.digits);
        System.out.println(randomString.nextString());
    }
}