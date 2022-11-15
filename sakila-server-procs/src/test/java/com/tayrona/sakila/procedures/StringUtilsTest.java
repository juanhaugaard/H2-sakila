package com.tayrona.sakila.procedures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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

    @Test
    void reverse_empty() {
        String expected = "";
        assertEquals(expected, StringUtils.reverse(""));
    }

    @Test
    void lPad_2() {
        String expected = "01";
        assertEquals(expected, StringUtils.lPad("1", 2, '0'));
    }
    @Test
    void lPad_1() {
        String expected = "1";
        assertEquals(expected, StringUtils.lPad("1", 1, '0'));
    }
    @Test
    void lPad_0() {
        String expected = "1";
        assertEquals(expected, StringUtils.lPad("1", 0, '0'));
    }
    @Test
    void lPad_null() {
        assertNull(StringUtils.lPad(null, 2, '0'));
    }
    @Test
    void lPad_empty() {
        String expected = "00";
        assertEquals(expected, StringUtils.lPad("", 2, '0'));
    }
}