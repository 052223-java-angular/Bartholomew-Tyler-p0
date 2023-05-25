package com.revature.app.screens;

import java.util.Scanner;

import com.revature.app.utils.Session;

import lombok.AllArgsConstructor;

/**
 * The MenuScreen class represents the menu screen of the Yolp Application.
 * It implements the IScreen interface.
 */
public class MenuScreen implements IScreen {
    private Session session;

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
        clearScreen();
        System.out.println("Welcome to the menu screen, " + session.getUsername() + "!");
        scan.nextLine();
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