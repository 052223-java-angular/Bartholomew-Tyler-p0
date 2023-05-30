package com.revature.app.screens;

import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.app.services.RouterService;
import com.revature.app.models.Cart;
import com.revature.app.models.CartProduct;
import com.revature.app.services.CartService;
import com.revature.app.utils.Session;
import com.revature.app.utils.StringHelper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CartScreen implements IScreen {
    RouterService routerService;
    CartService cartService;
    Session session;
    private static final Logger logger = LogManager.getLogger(CartScreen.class);

    @Override
    public void start(Scanner scan) {
        String input = "";
        String message = "";
        int minimumQuantity = 0;
        int maximumQuantity = 20;

        main: {
            while (true) {
                logger.info("Navigated to CartScreen");
                Cart cart = cartService.getCartFromUserId(session.getId());
                printCart(cart, false);

                if (!message.isEmpty()) {
                    System.out.println(message);
                }

                if (!cart.getCartProducts().isEmpty()) {
                    System.out.println("\n[1] Edit product quantity");
                    System.out.println("[2] Checkout");
                    System.out.println("[x] Exit");
                } else {
                    System.out.println("\n[x] Exit");

                }

                System.out.print("\nEnter: ");
                input = scan.nextLine();

                if (!cart.getCartProducts().isEmpty()) {
                    switch (input.toLowerCase()) {
                        case "1":
                            printCart(cart, true);
                            input = getNumericInputWithinRange(scan,
                                    "\nEnter product number to edit quantity (x to go back): ", 1,
                                    cart.getCartProducts().size());
                            if (input.equalsIgnoreCase("x")) {
                                message = "";
                                continue;
                            }
                            int productOption = Integer.parseInt(input);
                            CartProduct cartProduct = cart.getCartProducts().get(productOption - 1);
                            input = getNumericInputWithinRange(scan,
                                    "\nChange quanity of " + cartProduct.getProduct().getName() + " from "
                                            + cartProduct.getQuantity() + " to (x to go back, min: " + minimumQuantity
                                            + ", max: " + maximumQuantity + "): ",
                                    minimumQuantity,
                                    maximumQuantity);

                            if (input.equalsIgnoreCase("x")) {
                                message = "";
                                continue;
                            }

                            if (input.equalsIgnoreCase("0")) {
                                cartService.removeProductFromCart(cart.getId(), cartProduct.getProduct().getId());
                                logger.info(
                                        "Removed " + cartProduct.getProduct().getName() + " from "
                                                + session.getUsername()
                                                + "'s cart");
                                message = cartProduct.getProduct().getName() + " removed successfully.";
                                continue;
                            }

                            int updatedQuantity = Integer.parseInt(input);
                            cartService.updateQuantityOfProduct(cartProduct.getId(), updatedQuantity);
                            logger.info("Updated quantity of " + cartProduct.getProduct().getName() + " in "
                                    + session.getUsername() + "'s cart from " + cartProduct.getQuantity() + " to "
                                    + updatedQuantity);
                            message = cartProduct.getProduct().getName() + " quantity updated successfully.";
                            continue;
                        case "2":
                            logger.info("Navigating to checkout screen!");
                            routerService.navigate("/checkout", scan, cart);
                            break;
                        case "x":
                            break main;
                        default:
                            logger.warn("Invalid input on CartScreen!");
                            message = "Invalid input!";
                            continue;
                    }
                } else {
                    switch (input.toLowerCase()) {
                        case "x":
                            break main;
                        default:
                            logger.warn("Invalid input on CartScreen!");
                            message = "Invalid input!";
                            continue;
                    }
                }
            }
        }
    }

    private void printCart(Cart cart, boolean withIndex) {
        clearScreen();
        System.out.println("Cart");
        System.out.println("-----------------------------------------------------------------");
        printCartProducts(cart.getCartProducts(), withIndex);
    }

    private void printCartProducts(List<CartProduct> cartProducts, boolean withIndex) {
        if (cartProducts.size() < 1) {
            System.out.println("Your cart is empty!");
        } else {
            for (int i = 0; i < cartProducts.size(); i++) {
                CartProduct cartProduct = cartProducts.get(i);
                if (withIndex) {
                    System.out.printf("%5s %-40s %-10s\n",
                            "[" + (i + 1) + "]",
                            cartProduct.getProduct().getName(),
                            "$" + cartProduct.getProduct().getPrice());
                    System.out.printf("%5s <<%s>>\n", " ", cartProduct.getProduct().getCategory());
                    System.out.printf("%5s Qty: %s\n", " ", cartProduct.getQuantity());
                } else {
                    System.out.printf("%-40s %-10s\n",
                            cartProduct.getProduct().getName(),
                            "$" + cartProduct.getProduct().getPrice());
                    System.out.printf("<<%s>>\n", cartProduct.getProduct().getCategory());
                    System.out.printf("Qty: %s\n", cartProduct.getQuantity());
                }
                System.out.println();
            }
        }
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private String getNumericInputWithinRange(Scanner scan, String message, int lowerLimit, int upperLimit) {
        String input = "";

        while (true) {
            System.out.print(message);
            input = scan.nextLine();

            if (input.equalsIgnoreCase("x")) {
                return "x";
            }

            if (!StringHelper.isNumeric(input)) {
                System.out.println("Invalid input!");
                continue;
            }

            double inputDouble = Double.parseDouble(input);

            if (!StringHelper.isInteger(inputDouble)) {
                System.out.println("Invalid input!");
                continue;
            }

            if (inputDouble < lowerLimit || upperLimit < inputDouble) {
                System.out.println("Invalid input!");
                continue;
            }

            return input;
        }
    }
}
