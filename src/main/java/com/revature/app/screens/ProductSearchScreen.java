package com.revature.app.screens;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.app.services.RouterService;
import com.revature.app.utils.Session;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductSearchScreen implements IScreen {
    private static final Logger logger = LogManager.getLogger(ProductSearchScreen.class);
    private final RouterService routerService;
    private Session session;

    @Override
    public void start(Scanner scan) {
        String error = "";
        String input = "";

        exit: {
            while (true) {
                clearScreen();
                System.out.println("Product Search (press x to go back)");

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
        String searchString = "";
        while (true) {
            System.out.println("Enter a search term: ");
            searchString = scan.nextLine();
        }
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
