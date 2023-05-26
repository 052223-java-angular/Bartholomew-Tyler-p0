package com.revature.app.screens;

import java.util.Scanner;
import com.revature.app.services.RouterService;
import com.revature.app.utils.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import lombok.AllArgsConstructor;

/**
 * The MenuScreen class represents the menu screen of the Yolp Application.
 * It implements the IScreen interface.
 */
public class MenuScreen implements IScreen {
    private Session session;
    private static final Logger logger = LogManager.getLogger(RegisterScreen.class);
    private RouterService router;
    String input = "";
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
        System.out.println("Press x to logout and go back to the login screen");
        System.out.println("[1] Browse All Products");
        System.out.println("[2] Search for Products");
        input = scan.nextLine();

        
        switch(input.toLowerCase()) {
            case "1":
                logger.info("Browsing products");
                clearScreen();
                router.navigate("/browseproducts", scan);
                break;
            case "2":
                logger.info("Navigating to login screen");
                clearScreen();
                router.navigate("/searchproducts", scan);
                break;
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