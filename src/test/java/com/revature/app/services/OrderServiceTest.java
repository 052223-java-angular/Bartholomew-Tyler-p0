package com.revature.app.services;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.revature.app.models.OrderProduct;
import com.revature.app.models.CartProduct;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import java.math.BigDecimal;
import com.revature.app.models.Order;
import com.revature.app.daos.OrderDAO;
import java.util.ArrayList;
import com.revature.app.models.Cart;
import com.revature.app.models.User;
public class OrderServiceTest {
    

    @Mock
    private OrderDAO orderDao;

    private OrderService orderService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        orderService = new OrderService(orderDao);
    }

    @Test
    public void testGetUsersOrders() {
        ArrayList<Order> orders = new ArrayList<>();
        Order o1 = new Order("1", "1", BigDecimal.valueOf(28.00), "12/24/2022", null);
        Order o2 = new Order("2", "1",BigDecimal.valueOf(50.50),"01/23/2023", null);
        orders.add(o1);
        orders.add(o2);
        when(orderService.getAllUsersOrders("1")).thenReturn(orders);
        List<Order> returnedOrders = orderService.getAllUsersOrders("1");
        if(returnedOrders == null){
            fail();
        }else{
            assertTrue(returnedOrders.contains(o1));
            assertTrue(returnedOrders.contains(o2));
        }
    }

}
