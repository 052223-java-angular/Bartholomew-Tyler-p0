package com.revature.app.utils;

/**
 * StringHelper class holds a number of methods to assist in manipulating or
 * reading
 * strings throughout the program.
 */
public class StringHelper {
    /**
     * isNumeric is a method that ta check if a String is
     * a number
     * 
     * @param str string to be checked
     */
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * isInteger is a method to check if a double is actually an integer.
     * 
     * @param d double to be checked
     */
    public static boolean isInteger(double d) {
        return Math.floor(d) == d;
    }

    /**
     * Given how long some of the descriptions can be, wrapAndDisplay
     * allows a description to be presented to the user in a neat way
     * 
     * @param text The string of text to be manipulated
     */
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
        // Prints out the text to the terminal
        System.out.print(wrappedText);
    }
}
