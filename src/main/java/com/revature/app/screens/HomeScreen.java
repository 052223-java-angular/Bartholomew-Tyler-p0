package com.revature.app.screens;

import java.util.Scanner;

import com.revature.app.services.RouterService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HomeScreen implements IScreen {
    private final RouterService router;

    @Override
    public void start(Scanner scanner) {
        String input = "";

        exit: {
            while (true) {
                clearScreen();
                System.out.println("Welcome!");
                System.out.println("[x] Exit");

                System.out.print("\nEnter: ");
                input = scanner.nextLine();

                switch (input.toLowerCase()) {
                    case "x":
                        System.out.println("\nGoodbye!");
                        break exit;
                    default:
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