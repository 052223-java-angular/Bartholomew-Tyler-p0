package com.revature.app.services;
import com.revature.app.models.Order;
import com.revature.app.daos.OrderDAO;
import lombok.AllArgsConstructor;
import java.util.List;

@AllArgsConstructor
public class OrderService {
    private final OrderDAO orderDAO;

    public List<Order> getAllUsersOrders(String user_id) {
        return orderDAO.findUsersOrders(user_id);
    }
}
