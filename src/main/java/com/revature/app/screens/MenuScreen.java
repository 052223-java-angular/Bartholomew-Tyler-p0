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
    private RouterService router;
    private Session session;
    private static final Logger logger = LogManager.getLogger(MenuScreen.class);

    /**
     * Constructs a new MenuScreen with the specified Session.
     *
     * @param session the Session containing user information
     */
    public MenuScreen(Session session) {
        this.session = session;
    }

    @Override
    public void start(Scanner scan) {
        String input = "";
        String error = "";
        exit: {
            while (true) {
                clearScreen();
                System.out.println("Welcome to the menu screen, " + session.getUsername() + "!");

                if (!error.isBlank()) {
                    System.out.println(error);
                }
                System.out.println("Press x to logout and go back to the login screen");
                System.out.println("[1] Browse All Products");
                System.out.println("[2] Search for Products");

                System.out.print("\nEnter: ");
                input = scan.nextLine();

                switch (input.toLowerCase()) {
                    case "1":
                        logger.info("Browsing products");
                        clearScreen();
                        router.navigate("/browseproducts", scan);
                        break;
                    case "2":
                        logger.info("Navigating to Product Search screen");
                        router.navigate("/productsearch", scan);
                        break exit;
                    case "x":
                        logger.info("Exiting MenuScreen");
                        error = "";
                        System.out.println("\nGoodbye!");
                        session.clearSession();
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