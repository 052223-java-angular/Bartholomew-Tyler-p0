package com.revature.app.screens;

import java.util.List;
import lombok.AllArgsConstructor;
import com.revature.app.utils.Session;
import com.revature.app.utils.StringHelper;
import com.revature.app.services.OrderService;
import com.revature.app.services.RouterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Scanner;
import com.revature.app.models.Order;

/**
 * The OrderHistoryScreen class handles what information is presented to the
 * user when it
 * is navigated to. It implements the IScreen interface.
 */
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
        String message = "";
        String INVALID_INPUT_MSG = "Invalid input!";

        while (true) {
            clearScreen();
            System.out.println("Previous Orders");
            System.out.println(
                    "-----------------------------------------------------------------------------------------");
            List<Order> orders = orderService.getAllUsersOrders(session.getId());
            if (orders.size() > 0) {
                System.out.printf("%5s %40s %15s %26s\n", "", "Order Id", "Amount", "Date");
                for (int i = 0; i < orders.size(); i++) {
                    Order order = orders.get(i);
                    System.out.printf("%5s %40s %15s %26s\n", "[" + (i + 1) + "]",
                            order.getId(),
                            "$" + order.getAmount(),
                            order.getDate());
                }
            } else {
                System.out.println("You have no previous orders choom!");
            }

            if (!message.isEmpty()) {
                System.out.println("\n" + message);
            }

            System.out.print("\nEnter option to view order details (x to go back): ");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("x")) {
                message = "";
                break;
            }

            if (!StringHelper.isNumeric(input)) {
                message = INVALID_INPUT_MSG;
                continue;
            }

            double inputDouble = Double.parseDouble(input);

            if (!StringHelper.isInteger(inputDouble)) {
                message = INVALID_INPUT_MSG;
                continue;
            }

            if (inputDouble < 1 || orders.size() < inputDouble) {
                message = INVALID_INPUT_MSG;
                continue;
            }

            routerService.navigate("/orderdetails", scan, orders.get((int) inputDouble - 1));
        }
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
