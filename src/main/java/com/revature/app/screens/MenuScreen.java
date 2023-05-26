package com.revature.app.screens;

import java.util.Scanner;

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
    private Session session;
    private static final Logger logger = LogManager.getLogger(RegisterScreen.class);

    @Override
    public void start(Scanner scanner) {
        String input;
        String error = "";

        exit: {
            while (true) {
                clearScreen();
                System.out.println("Welcome to the menu screen, " + session.getUsername() + "!");
                if (!error.isBlank()) {
                    System.out.println(error);
                }
                System.out.println("\n[x] Logout");

                System.out.print("\nEnter: ");
                input = scanner.nextLine();

                switch (input.toLowerCase()) {
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