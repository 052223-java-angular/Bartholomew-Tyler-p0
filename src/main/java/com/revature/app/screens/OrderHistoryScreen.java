package com.revature.app.screens;

import java.util.List;
import lombok.AllArgsConstructor;
import com.revature.app.utils.Session;
import com.revature.app.services.OrderService;
import com.revature.app.services.RouterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Scanner;
import com.revature.app.models.Order;

@AllArgsConstructor
public class OrderHistoryScreen implements IScreen {
    private RouterService routerService;
    private OrderService orderService;
    private Session session;

    private static final Logger logger = LogManager.getLogger(MenuScreen.class);

    public OrderHistoryScreen(Session session) {
        this.session = session;
    }

    @Override
    public void start(Scanner scan) {
        String input = "";

        while (true) {
            clearScreen();
            System.out.println("View your previous orders here!");
            List<Order> orders = orderService.getAllUsersOrders(session.getId());
            if (orders.size() > 0) {
                System.out.printf("%5s %40s %15s %26s\n", "", "Order Id", "Amount", "Date");
                for (int i = 0; i < orders.size(); i++) {
                    Order order = orders.get(i);
                    System.out.printf("%5s %40s %15s %26s\n", "[" + (i + 1) + "]",
                            order.getId(),
                            order.getAmount(),
                            order.getDate());
                }
            } else {
                System.out.println("You have no previous orders choom!");
            }

            input = scan.nextLine();
            if (input.equalsIgnoreCase("x")) {
                break;
            }
        }
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
