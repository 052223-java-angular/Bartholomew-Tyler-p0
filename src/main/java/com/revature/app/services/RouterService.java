package com.revature.app.services;

import java.util.Scanner;

import com.revature.app.daos.OrderDAO;
import com.revature.app.daos.ProductDAO;
import com.revature.app.daos.UserDAO;
import com.revature.app.models.Product;
import com.revature.app.screens.BrowsingScreen;
import com.revature.app.screens.HomeScreen;
import com.revature.app.screens.RegisterScreen;
import com.revature.app.screens.LoginScreen;
import com.revature.app.screens.MenuScreen;
import com.revature.app.screens.OrderHistoryScreen;
import com.revature.app.screens.ProductScreen;
import com.revature.app.screens.ProductSearchScreen;

import lombok.AllArgsConstructor;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import com.revature.app.utils.Session;

@AllArgsConstructor
public class RouterService {
    private Session session;

    public void navigate(String path, Scanner scanner) {
        switch (path) {
            case "/home":
                new HomeScreen(this, session).start(scanner);
                break;
            case "/register":
                new RegisterScreen(this, getUserService(), getValidator(), session).start(scanner);
                break;
            case "/login":
                new LoginScreen(this, getUserService(), session).start(scanner);
                break;
            case "/menu":
                new MenuScreen(this, session).start(scanner);
                break;
            case "/browseproducts":
                new BrowsingScreen(this, getProductService(), session).start(scanner);
                break;
            case "/productsearch":
                new ProductSearchScreen(this, getProductService(), session).start(scanner);
                break;
            case "/product":
                new ProductScreen(this, getProductService(), session).start(scanner);
                break;
            case "/orderhistory":
                new OrderHistoryScreen(this, getOrderService(), session).start(scanner);
                break;
            default:
                break;
        }
    }

    private UserService getUserService() {
        return new UserService(new UserDAO());
    }

    private Validator getValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        return factory.getValidator();
    }

    private ProductService getProductService() {
        return new ProductService(new ProductDAO());
    }

    private OrderService getOrderService() {
        return new OrderService(new OrderDAO());
    }

}