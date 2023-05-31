package com.revature.app.screens;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lombok.AllArgsConstructor;

import com.revature.app.services.CartService;
import com.revature.app.services.ReviewService;
import com.revature.app.services.RouterService;
import com.revature.app.utils.Session;
import com.revature.app.utils.StringHelper;
import com.revature.app.models.Cart;
import com.revature.app.models.CartProduct;
import com.revature.app.models.Product;

/**
 * The ProductScreen class handles what information is presented to the user
 * when it
 * is navigated to. It implements the IScreen interface.
 */
@AllArgsConstructor
public class ProductScreen implements IScreen {
    private final RouterService routerService;
    private final CartService cartService;
    private final ReviewService reviewService;
    private final Product product;
    private final Session session;
    private static final Logger logger = LogManager.getLogger(ProductScreen.class);

    @Override
    public void start(Scanner scan) {
        String input = "";
        String message = "";
        int minimumQuantity = 1;
        int maximumQuantity = 20;
        String PRODUCT_ADDED_TO_CART_SUCCESS_MSG = "Item added to cart succesfully.";
        String PRODUCT_DELETED_FROM_CART_SUCCESS_MSG = "Item removed from cart succcessfully.";
        String INVALID_OPTION_MSG = "Invalid option!";
        boolean canAddToCart = false;
        boolean canLeaveReview = false;

        main: {
            while (true) {
                canLeaveReview = reviewService.getByUserIdAndProductId(session.getId(), product.getId()).isEmpty();
                logger.info("Navigated to ProductScreen");
                Cart cart = cartService.getCartFromUserId(session.getId());
                clearScreen();
                System.out.println("------------------- PRODUCT DETAILS --------------------");
                System.out.println("Name: " + product.getName());
                System.out.println("Category: " + product.getCategory());
                System.out.println("Price: $" + product.getPrice());
                System.out.println("Description:");
                StringHelper.wrapAndDisplay(product.getDescription());
                System.out.println("\n------------------------------------------------------");

                if (!productExistsInCart(product.getId(), cart)) {
                    canAddToCart = true;

                    String options = "";
                    if (canLeaveReview) {
                        options = "[a] Add to cart\n[r] Review this product\n[v] View Reviews";
                    } else {
                        options = "[a] Add to cart\n[v] View Reviews";
                    }
                    System.out.println(options);
                } else {
                    String options = "";
                    if (canLeaveReview) {
                        options = "[d] Remove from cart\n[r] Review this product\n[v] View Reviews";
                    } else {
                        options = "[d] Remove from cart\n[v] View Reviews";
                    }
                    System.out.println(options);
                }

                if (!message.isEmpty()) {
                    System.out.println("\n" + message);
                }

                System.out.print("\nEnter (x to cancel): ");
                input = scan.nextLine();

                switch (input.toLowerCase()) {
                    case "a":
                        if (!canAddToCart) {
                            message = "Product already in cart!";
                            break;
                        }
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
                                cartService.addProductToCart(cart.getId(), product.getId(), quantity);
                                logger.info("Added " + product.getName() + " to " + session.getUsername() + "'s cart");
                                message = PRODUCT_ADDED_TO_CART_SUCCESS_MSG;
                                canAddToCart = false;
                                break;
                            }

                            if (!StringHelper.isNumeric(input)) {
                                logger.warn("Invalid input on ProductScreen!");
                                addToCartMessage = INVALID_OPTION_MSG;
                                continue;
                            }

                            double inputDouble = Double.parseDouble(input);

                            if (!StringHelper.isInteger(inputDouble)) {
                                logger.warn("Invalid input on ProductScreen!");
                                addToCartMessage = INVALID_OPTION_MSG;
                                continue;
                            }

                            if (inputDouble < minimumQuantity || inputDouble > maximumQuantity) {
                                logger.warn("Invalid input on ProductScreen!");
                                addToCartMessage = "Quantity out of range!";
                                continue;
                            }

                            quantity = (int) inputDouble;

                            cartService.addProductToCart(cart.getId(), product.getId(), quantity);
                            canAddToCart = false;
                            logger.info("Added " + product.getName() + " to " + session.getUsername() + "'s cart");
                            message = PRODUCT_ADDED_TO_CART_SUCCESS_MSG;
                            break;
                        }
                        break;
                    case "d":
                        if (canAddToCart) {
                            message = "Product not in cart!";
                            break;
                        }
                        String deleteFromCartMessage = "";
                        deleteFromCart: {
                            while (true) {
                                if (!deleteFromCartMessage.isEmpty()) {
                                    System.out.println("\n" + deleteFromCartMessage);
                                }

                                System.out.print("Are you sure you want to remove this item from your cart? (y/n): ");

                                input = scan.nextLine();

                                switch (input.toLowerCase()) {
                                    case "y":
                                        cartService.removeProductFromCart(cart.getId(), product.getId());
                                        logger.info("Removed " + product.getName() + " from " + session.getUsername()
                                                + "'s cart");
                                        message = PRODUCT_DELETED_FROM_CART_SUCCESS_MSG;
                                        break deleteFromCart;
                                    case "n":
                                        break deleteFromCart;
                                    default:
                                        logger.warn("Invalid input on ProductScreen!");
                                        deleteFromCartMessage = INVALID_OPTION_MSG;
                                        break;
                                }
                            }
                        }
                        break;
                    case "r":
                        routerService.navigate("/createreview", scan, product);
                        break;
                    case "v":
                        routerService.navigate("/reviews", scan, product);
                        break;
                    case "x":
                        break main;
                    default:
                        message = INVALID_OPTION_MSG;
                        continue;
                }
            }
        }
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private boolean productExistsInCart(String productId, Cart cart) {
        for (CartProduct cartProduct : cart.getCartProducts()) {
            if (cartProduct.getProduct().getId().equals(productId)) {
                return true;
            }
        }
        return false;
    }
}
