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

        }
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
