package com.revature.app.utils;

public class StringHelper {
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isInteger(double d) {
        return Math.floor(d) == d;
    }
}
