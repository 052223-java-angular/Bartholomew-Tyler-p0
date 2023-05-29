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
        Cart cart = cartService.getCartFromUserId(session.getId());
        while (true) {
            clearScreen();
            System.out.println("Cart");
            System.out.println("-----------------------------------------------------------------");
            printCartProducts(cart.getCartProducts());

            System.out.print("\nEnter (x to go back): ");
            input = scan.nextLine();

            if (input.equalsIgnoreCase("x")) {
                break;
            }
        }
    }

    private void printCartProducts(List<CartProduct> cartProducts) {
        if (cartProducts.size() < 1) {
            System.out.println("Your cart is empty!");
        } else {
            for (int i = 0; i < cartProducts.size(); i++) {
                CartProduct cartProduct = cartProducts.get(i);
                System.out.printf("%5s %-40s %-10s\n",
                        "[" + (i + 1) + "]",
                        cartProduct.getProduct().getName(),
                        "$" + cartProduct.getProduct().getPrice());
                System.out.printf("%5s <<%s>>\n", " ", cartProduct.getProduct().getCategory());
                System.out.printf("%5s Qty: %s\n", " ", cartProduct.getQuantity());
            }
        }
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
