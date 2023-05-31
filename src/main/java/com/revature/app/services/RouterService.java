package com.revature.app.services;

import java.util.Scanner;

import com.revature.app.daos.OrderDAO;
import com.revature.app.daos.CartDAO;
import com.revature.app.daos.ProductDAO;
import com.revature.app.daos.ReviewDAO;
import com.revature.app.daos.UserDAO;
import com.revature.app.models.Product;
import com.revature.app.screens.BrowsingScreen;
import com.revature.app.screens.CartScreen;
import com.revature.app.screens.CreateReviewScreen;
import com.revature.app.screens.HomeScreen;
import com.revature.app.screens.RegisterScreen;
import com.revature.app.screens.ReviewsScreen;
import com.revature.app.screens.LoginScreen;
import com.revature.app.screens.MenuScreen;
import com.revature.app.screens.OrderDetailScreen;
import com.revature.app.screens.OrderHistoryScreen;
import com.revature.app.screens.ProductScreen;
import com.revature.app.screens.ProductSearchScreen;
import com.revature.app.screens.CheckoutScreen;
import com.revature.app.models.Cart;
import com.revature.app.models.Order;

import lombok.AllArgsConstructor;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import com.revature.app.utils.Session;

/**
 * The RouterService class handles the pathing for all of the screens that the
 * app uses
 */
@AllArgsConstructor
public class RouterService {
    private Session session;

    /**
     * navigate handles all of the necessary pathing in the app, passing along
     * the scanner for inputs and the paths to point to the screen that the
     * user is attempting to access. Different navigation paths take in
     * different parameters to ensure that they have the needed variables
     * and objects for the actions taken in those screens.
     */
    public void navigate(String path, Scanner scanner) {
        switch (path) {
            case "/cart":
                new CartScreen(this, getCartService(), session).start(scanner);
                break;
            case "/home":
                new HomeScreen(this, session).start(scanner);
                break;
            case "/register":
                new RegisterScreen(this, getUserService(), getCartService(), getValidator(), session).start(scanner);
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
            case "/orderhistory":
                new OrderHistoryScreen(this, getOrderService(), session).start(scanner);
                break;
            default:
                break;
        }
    }

    public void navigate(String path, Scanner scanner, Product product) {
        switch (path) {
            case "/createreview":
                new CreateReviewScreen(this, getReviewService(), product, session).start(scanner);
                break;
            case "/product":
                new ProductScreen(this, this.getCartService(), this.getReviewService(), product, session)
                        .start(scanner);
                break;
            case "/reviews":
                new ReviewsScreen(getReviewService(), getUserService(), product).start(scanner);
            default:
                break;
        }
    }

    public void navigate(String path, Scanner scanner, Cart cart) {
        switch (path) {
            case "/checkout":
                new CheckoutScreen(this.getCartService(), cart, getOrderService()).start(scanner);
                break;
            default:
                break;
        }
    }

    public void navigate(String path, Scanner scanner, Order order) {
        switch (path) {
            case "/orderdetails":
                new OrderDetailScreen(getProductService(), order).start(scanner);
                break;
            default:
                break;
        }
    }

    /**
     * All of our services and instantiated here to be passed into the above
     * navigation
     * screens. The validatior is also created here to be passed into the
     * registration screen.
     */
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

    private CartService getCartService() {
        return new CartService(new CartDAO(new UserDAO(), new ProductDAO()));
    }

    private ReviewService getReviewService() {
        return new ReviewService(new ReviewDAO());
    }

}