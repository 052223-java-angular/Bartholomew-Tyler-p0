package com.revature.app.services;

import com.revature.app.models.Order;
import com.revature.app.daos.OrderDAO;
import lombok.AllArgsConstructor;
import java.util.List;
import com.revature.app.models.Cart;
import com.revature.app.models.CartProduct;
import java.math.BigDecimal;
/**
* The OrderService class handles the buisness logic behind actions regarding 
* orders
*/
@AllArgsConstructor
public class OrderService {
    private final OrderDAO orderDAO;
    // getAllUsersOrders method returns a list of all of the orders whose user_id value
    // matches the user_id parameter
    public List<Order> getAllUsersOrders(String user_id) {
        return orderDAO.findUsersOrders(user_id);
    }
    // createNewOrder creates a new order by taking all of the cartproducts from the cart parameter
    // and obtains it's amount value
    public Order createNewOrder(Cart cart) {
        String user_id = cart.getUser().getId();
        List<CartProduct> cartProducts = cart.getCartProducts();
        BigDecimal amount = BigDecimal.ZERO;
        for (int i = 0; i < cartProducts.size(); i++) {
            CartProduct cartproduct = cartProducts.get(i);
            BigDecimal price = cartproduct.getProduct().getPrice().multiply(new BigDecimal(cartproduct.getQuantity()));
            amount = amount.add(price);
        }
        return orderDAO.save(user_id, amount);

    }
    // createOrderProducts transcribes all of a cart's cartproducts and creates copies of them
    // in the form of orderproducts which are associated with the order_id of the order
    // that is passed as a parameter
    public void createOrderProducts(String order_id, Cart cart) {
        List<CartProduct> cartProducts = cart.getCartProducts();
        for (int i = 0; i < cartProducts.size(); i++) {
            CartProduct cartproduct = cartProducts.get(i);
            String product_id = cartproduct.getProduct().getId();
            int quantity = cartproduct.getQuantity();
            orderDAO.addOrderProducts(order_id, product_id, quantity);
        }
    }
}
