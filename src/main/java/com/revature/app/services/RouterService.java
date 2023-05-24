package com.revature.app.services;

import java.util.Scanner;

import com.revature.app.daos.UserDAO;
import com.revature.app.screens.HomeScreen;
import com.revature.app.screens.RegisterScreen;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RouterService {
    public void navigate(String path, Scanner scanner) {
        switch (path) {
            case "/home":
                new HomeScreen(this).start(scanner);
                break;
            case "/register":
                new RegisterScreen(this, getUserService()).start(scanner);
                break;
            default:
                break;
        }
    }

    private UserService getUserService() {
        return new UserService(new UserDAO());
    }
}