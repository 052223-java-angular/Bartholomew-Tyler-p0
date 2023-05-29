package com.revature.app.screens;

import java.util.Scanner;
import lombok.AllArgsConstructor;

import com.revature.app.services.CartService;
import com.revature.app.services.ProductService;
import com.revature.app.services.RouterService;
import com.revature.app.utils.Session;
import com.revature.app.utils.StringHelper;
import com.revature.app.models.Product;

@AllArgsConstructor
public class ProductScreen implements IScreen {
    private final RouterService routerService;
    private final ProductService productService;
    private final CartService cartService;
    private final Session session;

    @Override
    public void start(Scanner scan) {
        String input = "";
        String message = "";
        int minimumQuantity = 1;
        int maximumQuantity = 10;

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
                System.out.println("[r] Review this product - [a] Add to cart");

                if (!message.isEmpty()) {
                    System.out.println("\n" + message);
                }

                System.out.print("\nEnter (x to cancel): ");
                input = scan.nextLine();

                switch (input.toLowerCase()) {
                    case "a":
                        String addToCartMessage = "";
                        int quantity = 1;
                        while (true) {
                            if (!addToCartMessage.isEmpty()) {
                                System.out.println("\n" + addToCartMessage);
                            }
                            System.out.print("Enter quantity between " + minimumQuantity + " and " + maximumQuantity
                                    + " (enter for " + minimumQuantity + ", x to cancel): ");
                            input = scan.nextLine();
                            if (input.equalsIgnoreCase("x")) {
                                break;
                            }

                            if (input.isBlank()) {
                                // cartService.addProductToCart()
                                message = "Item added to cart succesfully.";
                                break;
                            }

                            if (!StringHelper.isNumeric(input)) {
                                addToCartMessage = "Invalid option!";
                                continue;
                            }
                        }
                        break;
                    case "x":
                        session.clearProductSession();
                        break;
                    default:
                        message = "Invalid option!";
                        continue;
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
