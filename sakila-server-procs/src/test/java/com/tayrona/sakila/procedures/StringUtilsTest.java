package com.tayrona.sakila.procedures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringUtilsTest {

    @Test
    void reverse() {
        String expected = "0987654321";
        assertEquals(expected, StringUtils.reverse("1234567890"));
    }

    @Test
    void reverse_null() {
        String expected = null;
        assertEquals(expected, StringUtils.reverse(null));
    }
}