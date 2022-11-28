package org.tayrona.sakila.data.utils;

public interface StringUtils {
    char[] ZERO_LENGTH_CHAR_ARRAY = new char[0];
    String[] ZERO_LENGTH_STRING_ARRAY = new String[0];

    static String[] split(final String str, final char separatorChar) {
        if (null == str) {
            return ZERO_LENGTH_STRING_ARRAY;
        }
        return org.apache.commons.lang3.StringUtils.split(str, separatorChar);
    }

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

    static int countChars(String value, char target) {
        if (null == value) {
            return 0;
        }
        return countChars(toCharArray(value), target);
    }

    static int countChars(char[] value, char target) {
        int count = 0;
        if (null != value) {
            for (char b : value) {
                if (b == target) {
                    count += 1;
                }
            }
        }
        return count;
    }

    static boolean hasChars(String value, char target) {
        if (null == value) {
            return false;
        }
        return hasChars(toCharArray(value), target);
    }

    static boolean hasChars(char[] value, char target) {
        if (null != value) {
            for (char b : value) {
                if (b == target) {
                    return true;
                }
            }
        }
        return false;
    }

    static int countCharsInRange(String value, char lower, char upper) {
        if (null == value) {
            return 0;
        }
        return countCharsInRange(toCharArray(value), lower, upper);
    }

    static int countCharsInRange(char[] value, char lower, char upper) {
        int count = 0;
        if (null != value) {
            for (char b : value) {
                if ((lower <= b) && (b <= upper)) {
                    count += 1;
                }
            }
        }
        return count;
    }

    static boolean hasCharsInRange(String value, char lower, char upper) {
        if (null == value) {
            return false;
        }
        return hasCharsInRange(toCharArray(value), lower, upper);
    }

    static boolean hasCharsInRange(char[] value, char lower, char upper) {
        if (null != value) {
            for (char b : value) {
                if ((lower <= b) && (b <= upper)) {
                    return true;
                }
            }
        }
        return false;
    }

    static int countControlCharacters(String value) {
        if (null == value) {
            return 0;
        }
        return countControlCharacters(toCharArray(value));
    }

    static int countControlCharacters(char[] value) {
        return countCharsInRange(value, (char) 0, (char) 31) +
                countChars(value, (char) 127);
    }

    static boolean hasControlCharacters(String value) {
        if (null == value) {
            return false;
        }
        return hasControlCharacters(toCharArray(value));
    }

    static boolean hasControlCharacters(char[] value) {
        return hasCharsInRange(value, (char) 0, (char) 31) ||
                hasChars(value, (char) 127);
    }

    static int countPunctuationCharacters(String value) {
        if (null == value) {
            return 0;
        }
        return countPunctuationCharacters(toCharArray(value));
    }

    static int countPunctuationCharacters(char[] value) {
        return countCharsInRange(value, (char) 33, (char) 47) +
                countCharsInRange(value, (char) 58, (char) 64) +
                countCharsInRange(value, (char) 91, (char) 96) +
                countCharsInRange(value, (char) 123, (char) 126);
    }

    static boolean hasPunctuationCharacters(String value) {
        if (null == value) {
            return false;
        }
        return hasPunctuationCharacters(toCharArray(value));
    }

    static boolean hasPunctuationCharacters(char[] value) {
        return hasCharsInRange(value, (char) 33, (char) 47) ||
                hasCharsInRange(value, (char) 58, (char) 64) ||
                hasCharsInRange(value, (char) 91, (char) 96) ||
                hasCharsInRange(value, (char) 123, (char) 126);
    }

    static int countDigits(String value) {
        if (null == value) {
            return 0;
        }
        return countDigits(toCharArray(value));
    }

    static int countDigits(char[] value) {
        return countCharsInRange(value, (char) 48, (char) 57);
    }

    static boolean hasDigits(char[] value) {
        return hasCharsInRange(value, (char) 48, (char) 57);
    }

    static boolean hasDigits(String value) {
        if (null == value) {
            return false;
        }
        return hasDigits(toCharArray(value));
    }

    static int countForeignCharacters(String value) {
        if (null == value) {
            return 0;
        }
        return countForeignCharacters(toCharArray(value));
    }

    static int countForeignCharacters(char[] value) {
        int count = 0;
        if (null != value) {
            for (char b : value) {
                if (b > 127) {
                    count += 1;
                }
            }
        }
        return count;
    }

    static boolean hasForeignCharacters(String value) {
        if (null == value) {
            return false;
        }
        return hasForeignCharacters(toCharArray(value));
    }

    static boolean hasForeignCharacters(char[] value) {
        if (null != value) {
            for (char b : value) {
                if (b > 127) {
                    return true;
                }
            }
        }
        return false;
    }

    static boolean isUpperCase(char c) {
        return ((65 <= c) && (c <= 90));
    }

    static boolean isLowercase(char c) {
        return ((97 <= c) && (c <= 122));
    }

    static boolean isCapitalized(String word) {
        if ((null == word) || (word.length() < 1)) {
            return false;
        }
        boolean result = true;
        char[] chars = toCharArray(word);
        for (int i = 0; i < chars.length; i++) {
            if (i == 0) {
                result = result && isUpperCase(chars[i]);
            } else {
                result = result && isLowercase(chars[i]);
            }
            if (!result) {
                break;
            }
        }
        return result;
    }

    static boolean isAcceptableFilmTitle(String filmTitle) {
        if (null != filmTitle) {
            if (hasControlCharacters(filmTitle)) {
                return false;
            }
            filmTitle = filmTitle.trim();
            if (filmTitle.length() >= 1) {
                char[] chars = StringUtils.toCharArray(filmTitle);
                return !hasPunctuationCharacters(chars) &&
                        !hasForeignCharacters(chars);
            }
        }
        return false;
    }

    static boolean isAcceptableFullName(String fullName) {
        if (null != fullName) {
            if (hasControlCharacters(fullName)) {
                return false;
            }
            fullName = fullName.trim();
            if (fullName.length() >= 3) {
                char[] chars = StringUtils.toCharArray(fullName);
                boolean acceptableName = !hasPunctuationCharacters(chars) &&
                        !hasDigits(chars) &&
                        !hasForeignCharacters(chars);
                acceptableName = acceptableName && (countChars(chars, ' ') == 1);
                if (acceptableName) {
                    String[] names = fullName.split(" ");
                    acceptableName = (names[0].length() > 2) && (names[1].length() > 2);
                    if (acceptableName) {
                        acceptableName = isCapitalized(names[0]) && isCapitalized(names[1]);
                    }
                }
                return acceptableName;
            }
        }
        return false;
    }

    static char[] toCharArray(String value) {
        if (null == value) {
            return ZERO_LENGTH_CHAR_ARRAY;
        }
        char[] chars = new char[value.length()];
        if (value.length() > 0) {
            value.getChars(0, value.length(), chars, 0);
        }
        return chars;
    }
}
