package com.revature.app.screens;

import java.util.Scanner;
import com.revature.app.utils.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.revature.app.models.User;
import com.revature.app.services.RouterService;
import com.revature.app.services.UserService;
import java.util.Optional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoginScreen implements IScreen {
    private final RouterService routerService;
    private final UserService userService;
    private static final Logger logger = LogManager.getLogger(LoginScreen.class);
    private Session session;

    @Override
    public void start(Scanner scan) {
        String username = "";
        String password = "";

        exit: {
            while (true) {

                if (!session.getId().isBlank()) {
                    routerService.navigate("/menu", scan);
                    break exit;
                }

                logger.info("Beginning login!");
                clearScreen();
                System.out.println("Login here!");
                username = getUsername(scan);
                if (username.equals("x")) {
                    logger.info("Leaving login screen");
                    break exit;
                }
                password = getPassword(scan);
                if (password.equals("x")) {
                    logger.info("Leaving login screen");
                    break exit;
                }
                Optional<User> user = userService.login(username, password);
                if (user.isEmpty()) {
                    logger.warn("Unsuccessful login!");
                    System.out.println("Username or Password are incorrect, please try again");
                    System.out.print("\nPress enter to continue...");
                    scan.nextLine();
                    continue;

                }
                session.setSession(user.get());
                routerService.navigate("/menu", scan);
                break;
            }
        }
    }

    public String getUsername(Scanner scan) {
        String username = "";
        while (true) {
            System.out.print("\nEnter a username (x to go back): ");
            username = scan.nextLine();
            if (username.equalsIgnoreCase("x")) {
                return "x";
            }
            break;
        }

        return username;
    }

    public String getPassword(Scanner scan) {
        String password = "";
        while (true) {
            System.out.print("Enter your password (x to go back): ");
            password = scan.nextLine();
            if (password.equalsIgnoreCase("x")) {
                return "x";
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
