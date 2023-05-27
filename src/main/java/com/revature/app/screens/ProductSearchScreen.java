package com.revature.app.screens;

import java.util.List;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.app.services.ProductService;
import com.revature.app.services.RouterService;
import com.revature.app.utils.Session;
import com.revature.app.utils.StringHelper;
import com.revature.app.models.Product;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductSearchScreen implements IScreen {
    private static final Logger logger = LogManager.getLogger(ProductSearchScreen.class);
    private final RouterService routerService;
    private final ProductService productService;
    private Session session;

    @Override
    public void start(Scanner scan) {
        String error = "";
        String input = "";

        exit: {
            while (true) {
                clearScreen();
                System.out.println("Product Search (press x to go back at any time)");

                if (!error.isBlank()) {
                    System.out.println(error);
                }

                System.out.println("\nChooose a search option below:");
                System.out.println("[1] by name");
                System.out.println("[2] by category");
                System.out.println("[3] by price range");

                System.out.print("\nEnter: ");
                input = scan.nextLine();

                switch (input.toLowerCase()) {
                    case "1":
                        logger.info("Searching products by name");
                        searchProductsByName(scan);
                        break;
                    case "x":
                        logger.info("Exiting ProductSearchScreen");
                        error = "";
                        clearScreen();
                        routerService.navigate("/menu", scan);
                        break exit;
                    default:
                        logger.warn("Invalid input on ProductSearchScreen!");
                        error = "Invalid option!";
                        break;
                }
            }
        }
    }

    public void searchProductsByName(Scanner scan) {
        String input = "";
        String searchString = "";
        String error = "";

        while (true) {
            clearScreen();
            if (!error.isEmpty()) {
                System.out.println(error);
            }

            System.out.print("Enter a search term (x to go back): ");

            searchString = scan.nextLine();

            if (searchString.equalsIgnoreCase("x")) {
                break;
            }

            List<Product> products = productService.findProductsByName(searchString);

            if (products.size() > 0) {
                System.out.println("\nResults:");
                for (int i = 0; i < products.size(); i++) {
                    Product product = products.get(i);
                    System.out.printf("%5s %22s %15s %9.2f\n", "[" + (i + 1) + "]", product.getName(),
                            product.getCategory(),
                            product.getPrice());
                }
                System.out.print("\nChoose a product (x to go back): ");
            } else {
                error = "No results found";
                continue;
            }

            input = scan.nextLine();

            if (input.toLowerCase().equals("x")) {
                break;
            }

            while (true) {
                if (StringHelper.isNumeric(input)) {
                    // set variable in session, navigate to product screen
                } else {
                    switch (input.toLowerCase()) {
                        case "x":
                            break;
                        default:
                            logger.warn("Invalid input on MenuScreen!");
                            error = "Invalid option!";
                            continue;
                    }
                }
            }
        }
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
