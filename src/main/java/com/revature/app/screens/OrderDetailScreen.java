package com.revature.app.screens;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import com.revature.app.models.Order;
import com.revature.app.models.OrderProduct;
import com.revature.app.models.Product;
import com.revature.app.services.ProductService;

import lombok.AllArgsConstructor;

/**
 * The OrderDetailScreen class handles what information is presented to the user
 * when it
 * is navigated to. It implements the IScreen interface.
 */
@AllArgsConstructor
public class OrderDetailScreen implements IScreen {
    ProductService productService;
    Order order;

    @Override
    public void start(Scanner scan) {
        String message = "";

        while (true) {
            clearScreen();
            System.out.println("Order Details");
            System.out.println("===========================================================");

            if (!message.isEmpty()) {
                System.out.println("\n" + message + "\n");
            }
            printOrder(order);
            System.out.print("\nEnter x to go back: ");

            String input = scan.nextLine();

            if (input.equalsIgnoreCase("x")) {
                message = "";
                break;
            } else {
                message = "Invalid input!";
                continue;
            }
        }
    }

    private void printOrder(Order order) {
        System.out.println("Ordered on: " + order.getDate());
        System.out.println("Order Id: " + order.getId());
        System.out.printf("%-20s", "Grand Total: $" + order.getAmount() + "\n");
        printOrderProducts(order.getOrderProducts());

    }

    private void printOrderProducts(List<OrderProduct> orderProducts) {
        for (OrderProduct orderProduct : orderProducts) {
            Product product = productService.findProductById(orderProduct.getProduct_id());
            System.out.println("-----------------------------------------------------------");
            System.out.println(product.getName());
            System.out.println("Qty: " + orderProduct.getQuantity() + " @$" + product.getPrice() + " = $"
                    + product.getPrice().multiply(new BigDecimal(orderProduct.getQuantity())));
        }
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
