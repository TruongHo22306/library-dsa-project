package org.example.library.common;

public class Validate {
    private static String PHONE_REGEX = "^(\\+84|0)\\d{9,10}$";

    public static boolean isPhone(String phone) {
        return phone.matches(PHONE_REGEX);
    }
}
