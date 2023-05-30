package com.revature.app.services;
import com.revature.app.models.Order;
import com.revature.app.daos.OrderDAO;
import lombok.AllArgsConstructor;
import java.util.List;
import com.revature.app.models.Cart;
import com.revature.app.utils.Session;
import com.revature.app.models.CartProduct;
import com.revature.app.daos.CartDAO;
import java.math.BigDecimal;


@AllArgsConstructor
public class OrderService {
    private final OrderDAO orderDAO;

    public List<Order> getAllUsersOrders(String user_id) {
        return orderDAO.findUsersOrders(user_id);
    }

    public Order createNewOrder(Cart cart) {
        String user_id = cart.getUser().getId();
        String date = java.time.LocalDateTime.now().toString();
        List<CartProduct> cartProducts = cart.getCartProducts();
        BigDecimal amount = BigDecimal.ZERO;
        for(int i = 0; i < cartProducts.size(); i++) {
            CartProduct cartproduct = cartProducts.get(i);
            BigDecimal price = cartproduct.getProduct().getPrice();
            amount.add(price);
        }
        return orderDAO.save(user_id, date, amount);

    }

    public void createOrderProducts(String order_id, Cart cart) {
        List<CartProduct> cartProducts = cart.getCartProducts();
        for(int i = 0; i < cartProducts.size(); i++) {
            CartProduct cartproduct = cartProducts.get(i);
            String product_id = cartproduct.getProduct().getId();
            int quantity = cartproduct.getQuantity();
            orderDAO.addOrderProducts(order_id, product_id, quantity);
        }
    }
}
