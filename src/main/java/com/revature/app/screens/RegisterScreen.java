package com.revature.app.screens;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.app.services.RouterService;
import com.revature.app.services.UserService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
public class RegisterScreen implements IScreen {
    private final RouterService routerService;
    private final UserService userService;
    private static final Logger logger = LogManager.getLogger(RegisterScreen.class);
    
    @Override
    public void start(Scanner scan) {
       String input = "";
       String username = "";
       String password = "";

       exit : {
        while(true) {
            logger.info("Beginnning registration");
            clearScreen();
            System.out.println("Register here!");
            username = getUsername(scan);
            if (username.equals("x")) {
                logger.info("Leaving registration screen");
                break exit;
            }
            password = getPassword(scan);
                logger.info("Leaving registration screen");
            if (password.equals("x")) {
                break exit;
            }
            
            clearScreen();
            userService.register(username, password);
            routerService.navigate("/home", scan);
        }
       }
    }
    
    public String getUsername(Scanner scan) {
        String username = "";
        while (true) {
            System.out.print("\nEnter a username:");
            username = scan.nextLine();
            break;
        }

        if (username.equalsIgnoreCase("x")) {
            return "x";
        }

        return username;
    }

    public String getPassword(Scanner scan) {
        String password = "";
        String confirmPassword = "";
        while (true) {
            System.out.print("\nEnter a password:");
            password = scan.nextLine();
            System.out.print("\nPlease confirm your password:");
            confirmPassword = scan.nextLine();
            if (!password.equals(confirmPassword)) {
                logger.warn("Not matching passwords!");
                clearScreen();
                System.out.print("\nPasswords do not match!");
                continue;
            }
            break;
        }

        if (password.equalsIgnoreCase("x")) {
            return "x";
        }

        return password;
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
