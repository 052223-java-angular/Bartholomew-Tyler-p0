package com.revature.app.screens;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import com.revature.app.models.User;
import com.revature.app.services.RouterService;
import com.revature.app.services.UserService;

import javax.validation.Validator;
import javax.validation.ConstraintViolation;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegisterScreen implements IScreen {
    private final RouterService routerService;
    private final UserService userService;
    private final Validator validator;

    @Override
    public void start(Scanner scan) {
        String username = "";
        String password = "";

        exit: {
            while (true) {
                clearScreen();
                System.out.println("Register here!");

                username = getUsername(scan);
                if (username.equals("x")) {
                    break exit;
                }

                password = getPassword(scan);
                if (password.equals("x")) {
                    break exit;
                }

                clearScreen();
                userService.register(username, password);
                // routerService.navigate("/home", scan);
                break exit;
            }
        }
    }

    public String getUsername(Scanner scan) {
        String username = "";
        while (true) {
            System.out.print("\nEnter a username: ");
            username = scan.nextLine();

            if (username.equalsIgnoreCase("x")) {
                return "x";
            }

            Set<ConstraintViolation<User>> constraintViolations = validator.validateProperty(new User(username, ""),
                    "username");

            if (constraintViolations.size() > 0) {
                clearScreen();
                for (Iterator<ConstraintViolation<User>> i = constraintViolations.iterator(); i.hasNext();) {
                    System.out.println(i.next().getMessage());
                }
                continue;
            }

            if (!userService.isUniqueUsername(username)) {
                clearScreen();
                System.out.println("Username is not unique!");
                continue;
            }

            break;
        }

        return username;
    }

    public String getPassword(Scanner scan) {
        String password = "";
        String confirmPassword = "";
        while (true) {
            System.out.print("\nEnter a password: ");
            password = scan.nextLine();

            Set<ConstraintViolation<User>> constraintViolations = validator.validateProperty(new User("", password),
                    "password");

            if (constraintViolations.size() > 0) {
                clearScreen();
                for (Iterator<ConstraintViolation<User>> i = constraintViolations.iterator(); i.hasNext();) {
                    System.out.println(i.next().getMessage());
                }
                continue;
            }

            System.out.print("\nPlease confirm your password: ");
            confirmPassword = scan.nextLine();

            if (!password.equals(confirmPassword)) {
                clearScreen();
                System.out.print("\nPasswords do not match!");
                continue;
            }
            break;
        }

        return password;
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
