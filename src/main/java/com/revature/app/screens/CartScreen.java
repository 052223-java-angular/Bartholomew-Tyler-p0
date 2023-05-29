package com.revature.app.screens;

import java.util.List;
import java.util.Scanner;

import com.revature.app.services.RouterService;
import com.revature.app.models.Cart;
import com.revature.app.models.CartProduct;
import com.revature.app.services.CartService;
import com.revature.app.utils.Session;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CartScreen implements IScreen {
    RouterService routerService;
    CartService cartService;
    Session session;

    @Override
    public void start(Scanner scan) {
        String input = "";
        String message = "";
        Cart cart = cartService.getCartFromUserId(session.getId());
        main: {
            while (true) {
                clearScreen();
                System.out.println("Cart");
                System.out.println("-----------------------------------------------------------------");
                printCart(cart, false);

                if (!message.isEmpty()) {
                    System.out.println(message);
                }

                System.out.println("\n[1] Edit product quantity");
                System.out.println("[2] Checkout");
                System.out.println("[x] Exit");

                System.out.print("\nEnter: ");
                input = scan.nextLine();

                switch (input.toLowerCase()) {
                    case "1":
                        break;
                    case "2":
                        break;
                    case "x":
                        break main;
                    default:
                        message = "Invalid input!";
                        continue;
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

    private void printCartProductsWithIndex(List<CartProduct> cartProducts) {
        printCartProducts(cartProducts, true);
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
