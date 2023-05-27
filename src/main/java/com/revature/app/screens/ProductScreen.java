package com.revature.app.screens;

import java.util.Scanner;
import lombok.AllArgsConstructor;
import com.revature.app.services.ProductService;
import com.revature.app.services.RouterService;
import com.revature.app.utils.Session;
import com.revature.app.models.Product;

@AllArgsConstructor
public class ProductScreen implements IScreen {
    private final RouterService routerService;
    private final ProductService productService;
    private final Session session;

    @Override
    public void start(Scanner scan) {
        String input = "";
        exit: {
            while (true) {
                if (session.getProductId().isEmpty()) {
                    break;
                }
                clearScreen();
                Product product = productService.findProductById(session.getProductId());
                System.out.println("------------------- PRODUCT DETAILS --------------------");
                System.out.println("Name: " + product.getName());
                System.out.println("Category: " + product.getCategory());
                System.out.println("Price: $" + product.getPrice());
                System.out.println("Description:");
                wrapAndDisplay(product.getDescription());
                System.out.println("\n------------------------------------------------------");
                System.out.println("[R] Leave a review of this product - [A] To Add it to your cart");
                input = scan.nextLine();
                if (input.equalsIgnoreCase("x")) {
                    session.clearProductSession();
                    routerService.navigate("/menu", scan);
                    break exit;
                }
            }
        }
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

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
