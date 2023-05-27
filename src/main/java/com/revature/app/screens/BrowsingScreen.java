package com.revature.app.screens;

import java.util.Scanner;
import java.util.List;

import com.revature.app.services.ProductService;
import com.revature.app.services.RouterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.revature.app.utils.Session;
import lombok.AllArgsConstructor;
import com.revature.app.models.Product;

@AllArgsConstructor
public class BrowsingScreen implements IScreen {
    private RouterService routerService;
    private ProductService productService;
    private Session session;
    private static final Logger logger = LogManager.getLogger(RegisterScreen.class);

    public BrowsingScreen(Session session) {
        this.session = session;
    }

    @Override
    public void start(Scanner scan) {
        String input = "";

        exit: {
            while (true) {
                clearScreen();
                System.out.println("Welcome to the full inventory screen, solo!");
                List<Product> products = productService.findAllProducts();
                System.out.printf("%5s %40s %15s %10s\n", "", "Name", "Category", "Price");
                for (int i = 0; i < products.size(); i++) {
                    Product product = products.get(i);
                    System.out.printf("%5s %40s %15s %10.2f\n", "[" + (i + 1) + "]",
                            product.getName(),
                            product.getCategory(),
                            product.getPrice());
                }

                input = scan.nextLine();
                clearScreen();
                int index = -1;
                if (input.equalsIgnoreCase("x")) {
                    break exit;
                }

                try {
                    index = Integer.parseInt(input);
                    if (index >= 0 && index < products.size()) {
                        Product selectedProduct = products.get(index);
                        System.out.println("You have selected " + selectedProduct.getName());
                    } else {
                        System.out.println("Invalid option! Try again.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please try again");
                }
            }
        }
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
