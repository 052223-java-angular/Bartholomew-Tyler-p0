package com.revature.app.screens;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.app.services.RouterService;
import com.revature.app.utils.Session;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HomeScreen implements IScreen {
    private final RouterService router;
    private static final Logger logger = LogManager.getLogger(HomeScreen.class);
    private Session session;

    @Override
    public void start(Scanner scanner) {
        String input = "";
        logger.info("Navigated to home screen");

        exit: {
            while (true) {
                clearScreen();

                if (!session.getId().isBlank()) {
                    router.navigate("/menu", scanner);
                }

                System.out.println("Welcome!");
                System.out.println("To exit any screen at any point, enter x");
                System.out.println("\n[1] Register");
                System.out.println("[2] Login");
                System.out.println("[x] Exit");

                System.out.print("\nEnter: ");
                input = scanner.nextLine();

                switch (input.toLowerCase()) {

                    case "1":
                        logger.info("Navigating to register screen");
                        clearScreen();
                        router.navigate("/register", scanner);
                        break;
                    case "2":
                        logger.info("Navigating to login screen");
                        clearScreen();
                        router.navigate("/login", scanner);
                        break;
                    case "x":
                        logger.info("Exiting");
                        System.out.println("\nGoodbye!");
                        break exit;
                    default:
                        logger.warn("Invalid input!");
                        clearScreen();
                        System.out.println("Invalid option!");
                        System.out.print("\nPress enter to continue...");
                        scanner.nextLine();
                        break;
                }
            }
        }
    }

    /*
     * ------------------------ Helper methods ------------------------------
     */

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}