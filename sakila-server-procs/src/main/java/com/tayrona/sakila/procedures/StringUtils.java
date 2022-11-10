package com.tayrona.sakila.procedures;

public class StringUtils {
    private StringUtils() {
        // prevent instantiation
    }

    public static String reverse(String value) {
        if (null != value) {
            return (new StringBuilder(value)).reverse().toString();
        }
        return null;
    }
}
