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
                User user = new User("", "");
                clearScreen();
                System.out.println("Register here!");
                user.setUsername(getUsername(scan));
                if (username.equals("x")) {
                    break exit;
                }

                Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);

                for (Iterator<ConstraintViolation<User>> i = constraintViolations.iterator(); i.hasNext();) {
                    System.out.println(i.next().getMessage());
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
            System.out.print("\nEnter a password: ");
            password = scan.nextLine();
            System.out.print("\nPlease confirm your password: ");
            confirmPassword = scan.nextLine();
            if (!password.equals(confirmPassword)) {
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
