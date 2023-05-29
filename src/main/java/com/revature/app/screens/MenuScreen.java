package com.revature.app.screens;

import java.util.Scanner;

import com.revature.app.services.RouterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.revature.app.utils.Session;
import lombok.AllArgsConstructor;

/**
 * The MenuScreen class represents the menu screen of the Yolp Application.
 * It implements the IScreen interface.
 */
@AllArgsConstructor
public class MenuScreen implements IScreen {
    private RouterService routerService;
    private Session session;
    private static final Logger logger = LogManager.getLogger(MenuScreen.class);

    @Override
    public void start(Scanner scan) {
        String input = "";
        String error = "";
        exit: {
            while (true) {
                logger.info("Navigated to MenuScreen");
                clearScreen();
                System.out.println("Welcome to the menu screen, " + session.getUsername() + "!");

                if (!error.isBlank()) {
                    System.out.println(error);
                }
                System.out.println("Press x to logout and go back to the login screen");
                System.out.println("\n[1] Browse All Products");
                System.out.println("[2] Search for Products");
                System.out.println("[3] View Cart");
                System.out.println("[4] View Your Previous Orders");

                System.out.print("\nEnter: ");
                input = scan.nextLine();

                switch (input.toLowerCase()) {
                    case "1":
                        logger.info("Browsing products");
                        clearScreen();
                        routerService.navigate("/browseproducts", scan);
                        break;
                    case "2":
                        logger.info("Navigating to Product Search screen");
                        routerService.navigate("/productsearch", scan);
                        break exit;
                    case "3":
                        routerService.navigate("/cart", scan);
                        break;
                    case "4":
                        logger.info("Navigating to Order History screen");
                        routerService.navigate("/orderhistory", scan);
                        break;
                    case "x":
                        logger.info("Exiting MenuScreen");
                        error = "";
                        session.clearSession();
                        routerService.navigate("/home", scan);
                        break exit;
                    default:
                        logger.warn("Invalid input on MenuScreen!");
                        error = "Invalid option!";
                        break;
                }
            }
        }
    }
    /*
     * ------------------------ Helper methods ------------------------------
     */

    /**
     * Clears the console screen.
     */
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}