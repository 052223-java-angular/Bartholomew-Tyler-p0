package com.revature.app.screens;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import com.revature.app.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.app.services.CartService;
import com.revature.app.services.RouterService;
import com.revature.app.services.UserService;

import javax.validation.Validator;
import javax.validation.ConstraintViolation;
import lombok.AllArgsConstructor;
import com.revature.app.utils.Session;

/**
 * The RegisterScreen class handles what information is presented to the user
 * when it
 * is navigated to. It implements the IScreen interface.
 */
@AllArgsConstructor
public class RegisterScreen implements IScreen {
    private final RouterService routerService;
    private final UserService userService;
    private final CartService cartService;
    private final Validator validator;
    private static final Logger logger = LogManager.getLogger(RegisterScreen.class);
    private Session session;

    // Overrides default IScreen start method
    @Override
    public void start(Scanner scan) {
        String username = "";
        String password = "";

        exit: {
            // while loop to handle displaying information to the user
            while (true) {
                logger.info("Beginnning registration");
                clearScreen();
                System.out.println("Register here!");

                username = getUsername(scan);
                if (username.equals("x")) {
                    logger.info("Leaving registration screen");
                    break exit;
                }

                password = getPassword(scan);
                if (password.equals("x")) {
                    logger.info("Leaving registration screen");
                    break exit;
                }

                clearScreen();
                User user = userService.register(username, password);
                cartService.createCartFromUserId(user.getId());
                session.setSession(user);
                routerService.navigate("/menu", scan);
                break exit;
            }
        }
    }

    // retrieves username from user input for registration
    public String getUsername(Scanner scan) {
        String username = "";
        while (true) {
            System.out.print("\nEnter a username (x to go back): ");
            username = scan.nextLine();
            // option to leave screen
            if (username.equalsIgnoreCase("x")) {
                return "x";
            }
            // Validation methods to check if the username meets certain criteria
            Set<ConstraintViolation<User>> constraintViolations = validator.validateProperty(new User(username, ""),
                    "username");

            if (constraintViolations.size() > 0) {
                clearScreen();
                for (Iterator<ConstraintViolation<User>> i = constraintViolations.iterator(); i.hasNext();) {
                    System.out.println(i.next().getMessage());
                }
                continue;
            }
            // Checks to see if the username is already used in the database
            if (!userService.isUniqueUsername(username)) {
                clearScreen();
                System.out.println("Username is not unique!");
                continue;
            }

            break;
        }

        return username;
    }

    // Retrieves password from user input
    public String getPassword(Scanner scan) {
        String password = "";
        String confirmPassword = "";
        while (true) {
            System.out.print("\nEnter a password (x to go back): ");
            password = scan.nextLine();
            // Option to exit
            if (password.equalsIgnoreCase("x")) {
                return "x";
            }
            // Validator methods to make sure that password is acceptable
            Set<ConstraintViolation<User>> constraintViolations = validator.validateProperty(new User("", password),
                    "password");

            if (constraintViolations.size() > 0) {
                clearScreen();
                for (Iterator<ConstraintViolation<User>> i = constraintViolations.iterator(); i.hasNext();) {
                    System.out.println(i.next().getMessage());
                }
                continue;
            }
            // Have user type in password again for confirmation
            System.out.print("\nPlease confirm your password (x to go back): ");
            confirmPassword = scan.nextLine();
            // option to exit
            if (confirmPassword.equalsIgnoreCase("x")) {
                return "x";
            }
            // If the confirmed password does not match, the user is brought back to the
            // beginning
            if (!password.equals(confirmPassword)) {
                logger.warn("Not matching passwords!");
                clearScreen();
                System.out.print("\nPasswords do not match!");
                continue;
            }
            break;
        }

        return password;
    }

    // method to clear the screen at beginning of while loop
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
