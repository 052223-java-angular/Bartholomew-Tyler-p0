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

    // method for wrapping description text in a next and orderly way
    public static void wrapAndDisplay(String text) {
        int maxLineLength = 60; // Maximum line length for description

        // Wrap the text
        StringBuilder wrappedText = new StringBuilder();
        int currentIndex = 0;
        while (currentIndex < text.length()) {
            if (currentIndex + maxLineLength < text.length()) {
                // Find the last space within the line length
                int lastSpaceIndex = text.lastIndexOf(' ', currentIndex + maxLineLength);

                if (lastSpaceIndex != -1 && lastSpaceIndex > currentIndex) {
                    wrappedText.append(text, currentIndex, lastSpaceIndex);
                    wrappedText.append(System.lineSeparator());
                    currentIndex = lastSpaceIndex + 1;
                } else {
                    // If no space is found, break the line at the line length
                    wrappedText.append(text, currentIndex, currentIndex + maxLineLength);
                    wrappedText.append(System.lineSeparator());
                    currentIndex += maxLineLength;
                }
            } else {
                // Add the remaining part of the text
                wrappedText.append(text.substring(currentIndex));
                currentIndex = text.length();
            }
        }

        System.out.print(wrappedText);
    }
}
