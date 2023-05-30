package com.revature.app.screens;
import lombok.AllArgsConstructor;
import java.util.Scanner;
import com.revature.app.models.Cart;
import com.revature.app.utils.Session;
import com.revature.app.utils.StringHelper;
import com.revature.app.services.CartService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;
import com.revature.app.services.OrderService;
import com.revature.app.models.Order;
import java.util.List;
import com.revature.app.models.CartProduct;

@AllArgsConstructor
public class CheckoutScreen implements IScreen {
    private final CartService cartService;
    private final Cart cart;
    private final Session session;
    private final OrderService orderService;

    // String input = "";
    @Override
    public void start(Scanner scan) {
        String input = "";
        String message = "";
        exit: {
            while(true) {
                clearScreen();
                System.out.println("Welcome to the checkout screen!");
                System.out.println(message);
                System.out.print("Enter your credit card number:");
                input = scan.nextLine();
                if(input.equalsIgnoreCase("x")) {
                    break exit;
                }
                if(!StringHelper.isNumeric(input)) {
                    // System.out.print("Credit card number must valid!");
                    message = "Credit card number must valid!";
                    continue;
                }
                if (!isCardValid(input)) {
                    // System.out.print("Credit card number must be valid!");
                    message = "Credit card number must valid!";
                    continue;
                }
                System.out.print("Enter your expiration date (MM/yy format):");
                input = scan.nextLine();
                if(!isExpirationDateValid(input)) {
                    message = "Invalid expiration date!";
                    continue;
                }
                System.out.print("Enter your card's three or four digit CVV number:");
                input = scan.nextLine();
                if(!StringHelper.isNumeric(input)) {
                    message = "CVV number must be valid!";
                    continue;
                }
                if(!(input.length() == 3 || input.length() == 4)) {
                    message = "CVV number must be valid!";
                    continue;
                }
                Order order = orderService.createNewOrder(cart);
                String order_id = order.getId();
                orderService.createOrderProducts(order_id, cart);
                clearCart(cart);
                message = "Processing order....";
                wait(5000);
                break exit;
            }
        }
        
        
    }
    

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static boolean isExpirationDateValid(String expirationDate) {
                // Define a regex pattern for MM/yy format
                String pattern = "^(0[1-9]|1[0-2])/(\\d{2})$";

                // Check if the expiration date matches the pattern
                if (!Pattern.matches(pattern, expirationDate)) {
                    return false; // Invalid format
                }
        
                // Extract month and year from the expiration date
                String[] parts = expirationDate.split("/");
                int month = Integer.parseInt(parts[0]);
                int year = Integer.parseInt(parts[1]);
        
                // Perform additional checks for month and year values
                if (month < 1 || month > 12) {
                    return false; // Invalid month
                }
        
                int currentYear = java.time.Year.now().getValue() % 100; // Get the current two-digit year
        
                if (year < currentYear || (year == currentYear && month < java.time.MonthDay.now().getMonthValue())) {
                    return false; // Expired date
                }
        
                // All checks passed, expiration date is valid
                return true;
            }
        

    
    
    public static boolean isCardValid(String cardNumber) {
        if (cardNumber.length() != 16) {
            return false;
        }
        return true;
    }

    public void clearCart(Cart cart) {
        List<CartProduct> cartProducts = cart.getCartProducts();
        for (int i = 0; i < cartProducts.size(); i++) {
            CartProduct cartproduct = cartProducts.get(i);
            String product_id = cartproduct.getProduct().getId();
            cartService.removeProductFromCart(cart.getId(), product_id);
        }
    }

    public static void wait(int ms)
{
    try
    {
        Thread.sleep(ms);
    }
    catch(InterruptedException ex)
    {
        Thread.currentThread().interrupt();
    }
}
}
