package com.revature.app;

import com.revature.app.services.RouterService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

// import com.revature.app.utils.ConnectionFactory;
import com.revature.app.utils.Session;

/**
 * The class App is the class that starts the program
 * It creates a new Scanner to help handle inputs
 * and it creates a router to aid in navigating
 * through the app.
 */
public class App {
  public static void main(String args[]) throws ClassNotFoundException, IOException, SQLException {
    // System.out.println(ConnectionFactory.getInstance().getConnection());
    Scanner scanner = new Scanner(System.in);
    RouterService router = new RouterService(new Session("", ""));
    router.navigate("/home", scanner);
    scanner.close();
  }
}
