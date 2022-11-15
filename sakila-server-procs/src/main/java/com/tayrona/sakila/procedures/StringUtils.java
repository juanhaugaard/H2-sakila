package com.tayrona.sakila.procedures;

public interface StringUtils {

    static String reverse(String value) {
        if (null != value) {
            return (new StringBuilder(value)).reverse().toString();
        }
        return null;
    }

    static String repeat(char value, int times) {
        if (times < 1) {
            return "";
        }
        char[] buffer = new char[times];
        for (int i = 0; i < times; i++) {
            buffer[i] = value;
        }
        return new String(buffer);
    }

    static String lPad(String value, int size) {
        return lPad(value, size, ' ');
    }

    static String lPad(String value, int size, char padding) {
        if (null == value) {
            return null;
        }
        if (size <= value.length()) {
            return value;
        }
        return repeat(padding, size - value.length()) + value;
    }
}
