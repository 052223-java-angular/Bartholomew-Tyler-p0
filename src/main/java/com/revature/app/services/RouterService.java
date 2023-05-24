package com.revature.app.services;

import java.util.Scanner;

import com.revature.app.screens.HomeScreen;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RouterService {
    public void navigate(String path, Scanner scanner) {
        switch (path) {
            case "/home":
                new HomeScreen(this).start(scanner);
            default:
                break;
        }
    }
}