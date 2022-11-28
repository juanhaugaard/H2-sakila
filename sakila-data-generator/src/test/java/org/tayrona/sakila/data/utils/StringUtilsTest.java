package org.tayrona.sakila.data.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringUtilsTest {

    @Test
    void reverse() {
        assertEquals("7654321", StringUtils.reverse("1234567"));
        assertEquals("", StringUtils.reverse(""));
        assertNull(StringUtils.reverse(null));
    }

    @Test
    void repeat() {
        assertEquals("", StringUtils.repeat('0', 0));
        assertEquals("00000", StringUtils.repeat('0', 5));
        assertEquals("000000000000", StringUtils.repeat('0', 12));
    }

    @Test
    void lPad() {
        assertNull(StringUtils.lPad(null, 12));
        assertEquals("", StringUtils.lPad("", 0));
        assertEquals("1", StringUtils.lPad("1", 1));
        assertEquals("            ", StringUtils.lPad("", 12));
        assertEquals("           1", StringUtils.lPad("1", 12));
        assertEquals("    12345678", StringUtils.lPad("12345678", 12));
    }
    @Test
    void countChars() {
        assertEquals(0, StringUtils.countChars((String)null,' '));
        assertEquals(0, StringUtils.countChars("",' '));
        assertEquals(0, StringUtils.countChars("Hello",' '));
        assertEquals(1, StringUtils.countChars("Hello World",' '));
        assertEquals(2, StringUtils.countChars("Hello Cruel World",' '));
    }
    @Test
    void hasChars() {
        assertFalse(StringUtils.hasChars((String)null,' '));
        assertFalse(StringUtils.hasChars("",' '));
        assertFalse(StringUtils.hasChars("Hello",' '));
        assertTrue(StringUtils.hasChars("Hello World",' '));
        assertTrue(StringUtils.hasChars("Hello Cruel World",' '));
    }
    @Test
    void countCharsInRange() {
        assertEquals(0, StringUtils.countCharsInRange((String)null,'!','&'));
        assertEquals(0, StringUtils.countCharsInRange("",'!','&'));
        assertEquals(0, StringUtils.countCharsInRange("Hello",'!','&'));
        assertEquals(1, StringUtils.countCharsInRange("Hello#World",'!','&'));
        assertEquals(2, StringUtils.countCharsInRange("Hello!Cruel%World",'!','&'));
    }
    @Test
    void hasCharsInRange() {
        assertFalse(StringUtils.hasCharsInRange((String)null,'!','&'));
        assertFalse(StringUtils.hasCharsInRange("",'!','&'));
        assertFalse(StringUtils.hasCharsInRange("Hello",'!','&'));
        assertTrue(StringUtils.hasCharsInRange("Hello#World",'!','&'));
        assertTrue(StringUtils.hasCharsInRange("Hello!Cruel%World",'!','&'));
    }
    @Test
    void countControlChars() {
        assertEquals(0, StringUtils.countControlCharacters((String)null));
        assertEquals(0, StringUtils.countControlCharacters(""));
        assertEquals(0, StringUtils.countControlCharacters("Hello"));
        assertEquals(1, StringUtils.countControlCharacters("Hello\rWorld"));
        assertEquals(2, StringUtils.countControlCharacters("Hello\rCruel\fWorld"));
    }
    @Test
    void hasControlChars() {
        assertFalse(StringUtils.hasControlCharacters((String)null));
        assertFalse(StringUtils.hasControlCharacters(""));
        assertFalse(StringUtils.hasControlCharacters("Hello"));
        assertTrue(StringUtils.hasControlCharacters("Hello\rWorld"));
        assertTrue(StringUtils.hasControlCharacters("Hello\rCruel\fWorld"));
    }
    @Test
    void countPunctuationCharacters() {
        assertEquals(0, StringUtils.countPunctuationCharacters((String)null));
        assertEquals(0, StringUtils.countPunctuationCharacters(""));
        assertEquals(0, StringUtils.countPunctuationCharacters("Hello"));
        assertEquals(1, StringUtils.countPunctuationCharacters("Hello#World"));
        assertEquals(2, StringUtils.countPunctuationCharacters("Hello!Cruel%World"));
    }
    @Test
    void hasPunctuationCharacters() {
        assertFalse(StringUtils.hasPunctuationCharacters((String)null));
        assertFalse(StringUtils.hasPunctuationCharacters(""));
        assertFalse(StringUtils.hasPunctuationCharacters("Hello"));
        assertTrue(StringUtils.hasPunctuationCharacters("Hello#World"));
        assertTrue(StringUtils.hasPunctuationCharacters("Hello!Cruel%World"));
    }
    @Test
    void countForeignCharacters() {
        assertEquals(0, StringUtils.countForeignCharacters((String)null));
        assertEquals(0, StringUtils.countForeignCharacters(""));
        assertEquals(3, StringUtils.countForeignCharacters("Þórunn Sveinsdóttir"));
    }
    @Test
    void hasForeignCharacters() {
        assertFalse(StringUtils.hasForeignCharacters((String)null));
        assertFalse(StringUtils.hasForeignCharacters(""));
        assertTrue(StringUtils.hasForeignCharacters("Þórunn Sveinsdóttir"));
    }

    @Test
    void isCapitalized() {
        assertFalse(StringUtils.isCapitalized(null));
        assertFalse(StringUtils.isCapitalized(""));
        assertTrue(StringUtils.isCapitalized("Capitalized"));
        assertFalse(StringUtils.isCapitalized("capitalized"));
        assertFalse(StringUtils.isCapitalized("capitaLized"));
    }
    @Test
    void countDigits() {
        assertEquals(0, StringUtils.countDigits((String)null));
        assertEquals(0, StringUtils.countDigits(""));
        assertEquals(0, StringUtils.countDigits("Hello World"));
        assertEquals(1, StringUtils.countDigits("Hello 3rd World"));
        assertEquals(2, StringUtils.countDigits("Hello 3rd World from the 1st World"));
    }
    @Test
    void hasDigits() {
        assertFalse(StringUtils.hasDigits((String)null));
        assertFalse(StringUtils.hasDigits(""));
        assertFalse(StringUtils.hasDigits("Hello World"));
        assertTrue(StringUtils.hasDigits("Hello 3rd World"));
        assertTrue(StringUtils.hasDigits("Hello 3rd World from the 1st World"));
    }
    @Test
    void isAcceptableFilmTitle() {
        assertFalse(StringUtils.isAcceptableFilmTitle(null));
        assertFalse(StringUtils.isAcceptableFilmTitle(""));
        assertFalse(StringUtils.isAcceptableFilmTitle("Þórunn Sveinsdóttir"));
        assertFalse(StringUtils.isAcceptableFilmTitle("The Green Giant!"));
        assertFalse(StringUtils.isAcceptableFilmTitle("The Green Giant\r"));
        assertTrue(StringUtils.isAcceptableFilmTitle("The Green Giant"));
        assertTrue(StringUtils.isAcceptableFilmTitle("the green giant"));
    }
    @Test
    void isAcceptableFullName() {
        assertFalse(StringUtils.isAcceptableFullName(null));
        assertFalse(StringUtils.isAcceptableFullName(""));
        assertFalse(StringUtils.isAcceptableFullName("Þórunn Sveinsdóttir"));
        assertFalse(StringUtils.isAcceptableFullName("Green Giant!"));
        assertFalse(StringUtils.isAcceptableFullName("Green Giant\r"));
        assertFalse(StringUtils.isAcceptableFullName("green giant"));
        assertFalse(StringUtils.isAcceptableFullName("The Green Giant"));
        assertTrue(StringUtils.isAcceptableFullName("Green Giant"));
    }
    @Test
    void toCharArray() {
        assertEquals(0, StringUtils.toCharArray(null).length);
        assertEquals(0, StringUtils.toCharArray("").length);
        assertEquals(11, StringUtils.toCharArray("Hello World").length);
        assertEquals(19, StringUtils.toCharArray("Þórunn Sveinsdóttir").length);
    }
    @Test
    void split() {
        assertEquals(0, StringUtils.split(null, ' ').length);
        assertEquals(0, StringUtils.split("", ' ').length);
        assertEquals(1, StringUtils.split("Hello", ' ').length);
        assertEquals(2, StringUtils.split("Hello World", ' ').length);
        assertEquals(2, StringUtils.split("Hello World ", ' ').length);
        assertEquals(2, StringUtils.split("Hello  World", ' ').length);
    }
}