package com.tayrona.sakila.procedures;

public interface StringUtils {

    static String reverse(String value) {
        if (null != value) {
            return (new StringBuilder(value)).reverse().toString();
        }
        return null;
    }
}
