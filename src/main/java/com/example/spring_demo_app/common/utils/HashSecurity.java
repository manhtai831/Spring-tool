package com.example.spring_demo_app.common.utils;

import org.springframework.security.crypto.codec.Hex;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashSecurity {
    public static String SHA256 = "SHA-256";
    public static String MD5 = "MD5";

    public static String hash(String plainText, String hashType) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(hashType);
        byte[] bytes = digest.digest(plainText.getBytes(StandardCharsets.UTF_8));
        final StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            final String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1)
                hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }


    public static String privateHash(String plainText) {

        byte[] bytes = plainText.getBytes();
        return String.valueOf(Hex.encode(bytes));

    }

    public static String unHash(String hashed) {


        StringBuilder output = new StringBuilder();
        for (int i = 0; i < hashed.length(); i += 2) {
            String str = hashed.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
        }
        return output.toString().trim();

    }


    public static void main(String[] args) throws NoSuchAlgorithmException {

//        System.out.println(unHash(unHash("33383334333933343333333533373334333533353336")));
//        System.out.println(unHash(unHash("33383334333933373333333533383339333133323336")));
//        System.out.println(unHash(unHash("33383334333333373338333033343331333533333331")));
//        System.out.println(unHash(unHash("33383334333533383339333433323337333933363339")));
//        System.out.println(unHash(unHash("33383334333333373332333133313339333733323338")));
//        System.out.println(unHash(unHash("33383334333933373333333433363333333233393337")));
//
//        System.out.println(unHash(unHash("33383334333933383338333833373332333733313332")));
//        System.out.println(unHash(unHash("33383334333833333334333333333330333333383338")));
//        System.out.println(hash(hash("",MD5),SHA256));
//        System.out.println(privateHash(privateHash("84988872712")));
    }
}


