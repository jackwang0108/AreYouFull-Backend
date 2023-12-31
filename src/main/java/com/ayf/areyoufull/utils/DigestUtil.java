package com.ayf.areyoufull.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Random;

public class DigestUtil {
    private static final String encodingCharset = "UTF-8";

    public static String hmacSign(String aValue, int length) {
        String md5Str = hmacSign(aValue);
        if (md5Str.length() < length) {
            length = md5Str.length();
        }
        return md5Str.substring(0, length);
    }

    public static String hmacSign(String aValue) {
        return hmacSign(aValue, "areyoufull");
    }

    public static String hmacSign(String aValue, String aKey) {
        byte[] k_ipad = new byte[64];
        byte[] k_opad = new byte[64];
        byte[] keyb;
        byte[] value;
        try {
            keyb = aKey.getBytes(encodingCharset);
            value = aValue.getBytes(encodingCharset);
        } catch (UnsupportedEncodingException e) {
            keyb = aKey.getBytes();
            value = aValue.getBytes();
        }

        Arrays.fill(k_ipad, keyb.length, 64, (byte) 54);
        Arrays.fill(k_opad, keyb.length, 64, (byte) 92);
        for (int i = 0; i < keyb.length; i++) {
            k_ipad[i] = (byte) (keyb[i] ^ 0x36);
            k_opad[i] = (byte) (keyb[i] ^ 0x5c);
        }

        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {

            return null;
        }
        md.update(k_ipad);
        md.update(value);
        byte dg[] = md.digest();
        md.reset();
        md.update(k_opad);
        md.update(dg, 0, 16);
        dg = md.digest();
        return toHex(dg);
    }

    public static int randomCode() {
        //0550
        //550
        //0 - 8999
        //0+1000  1000
        //8999   1000  9999
        return new Random().nextInt(9000) + 1000;
    }

    public static String toHex(byte[] input) {
        if (input == null)
            return null;
        StringBuilder output = new StringBuilder(input.length * 2);
        for (byte b : input) {
            int current = b & 0xff;
            if (current < 16)
                output.append("0");
            output.append(Integer.toString(current, 16));
        }
        return output.toString();
    }

    public static String getHmac(String[] args, String key) {
        if (args == null || args.length == 0) {
            return (null);
        }
        StringBuilder str = new StringBuilder();
        for (String arg : args) {
            str.append(arg);
        }
        return (hmacSign(str.toString(), key));
    }

    public static String digest(String aValue) {
        aValue = aValue.trim();
        byte[] value;
        try {
            value = aValue.getBytes(encodingCharset);
        } catch (UnsupportedEncodingException e) {
            value = aValue.getBytes();
        }
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        return toHex(md.digest(value));

    }

    public static void main(String[] args) {
        System.out.println();
    }
}


