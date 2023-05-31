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
import com.revature.app.models.Product;

/**
 * This is the OrderServiceTest class, it contains
 * the methods necessary to test OrderService methods
 */
public class OrderServiceTest {
    /**
     * Here we mock our OrderDAO and orderService
     * in order to test their associated methods
     */
    @Mock
    private OrderDAO orderDao;

    private OrderService orderService;

    /**
     * setUp creates the environment for tests to be run
     */
    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        orderService = new OrderService(orderDao);
    }

    /**
     * testGetUsersOrders allows us to test the ability of the app to
     * retrieve a list of all of the orders that an individual made in the past
     */
    @Test
    public void testGetUsersOrders() {
        ArrayList<Order> orders = new ArrayList<>();
        Order o1 = new Order("1", "1", BigDecimal.valueOf(28.00), "12/24/2022", null);
        Order o2 = new Order("2", "1", BigDecimal.valueOf(50.50), "01/23/2023", null);
        orders.add(o1);
        orders.add(o2);
        when(orderService.getAllUsersOrders("1")).thenReturn(orders);
        List<Order> returnedOrders = orderService.getAllUsersOrders("1");
        if (returnedOrders == null) {
            fail();
        } else {
            assertTrue(returnedOrders.contains(o1));
            assertTrue(returnedOrders.contains(o2));
        }
    }

    /**
     * testCreatingOrderProducts tests the ability of the app to make OrderProducts,
     * this is
     * important because OrderProducts are made via transcribing the data from
     * products in the
     * cart before attaching them to a newly made Order.
     */
    @Test
    public void testCreatingOrderProducts() {
        User u1 = new User("1", "testUser", "testPassword");
        Product p1 = new Product("1", "grapes", "fruit", BigDecimal.valueOf(28.00), "a fruit");
        Product p2 = new Product("2", "shoe", "footwear", BigDecimal.valueOf(50.50), "basketball shoes");
        CartProduct cpr1 = new CartProduct("1", "1", p1, 3);
        CartProduct cpr2 = new CartProduct("2", "1", p2, 2);
        List<CartProduct> cartProducts = new ArrayList<>();
        cartProducts.add(cpr1);
        cartProducts.add(cpr2);
        Cart cart = new Cart("1", u1, cartProducts);

        doNothing().when(orderDao).addOrderProducts("1", p1.getId(), cpr1.getQuantity());
        doNothing().when(orderDao).addOrderProducts("1", p2.getId(), cpr2.getQuantity());
        orderService.createOrderProducts("1", cart);

        verify(orderDao, times(1)).addOrderProducts("1", p1.getId(), cpr1.getQuantity());
        verify(orderDao, times(1)).addOrderProducts("1", p2.getId(), cpr2.getQuantity());
    }

    /**
     * testCreatingAnOrder checks the ability of the app to make new Orders based
     * off
     * of the contents of the cart object.
     */
    @Test
    public void testCreatingAnOrder() {
        User u1 = new User("1", "testUser", "testPassword");
        Product p1 = new Product("1", "grapes", "fruit", BigDecimal.valueOf(28.00), "a fruit");
        Product p2 = new Product("2", "shoe", "footwear", BigDecimal.valueOf(50.50), "basketball shoes");
        CartProduct cpr1 = new CartProduct("1", "1", p1, 3);
        CartProduct cpr2 = new CartProduct("2", "1", p2, 2);
        List<CartProduct> cartProducts = new ArrayList<>();
        cartProducts.add(cpr1);
        cartProducts.add(cpr2);
        Cart cart = new Cart("1", u1, cartProducts);
        Order order = new Order("1", "1", BigDecimal.valueOf(185.00), "12/25/2022", null);
        when(orderService.createNewOrder(cart)).thenReturn(order);

        Order resultOrder = orderService.createNewOrder(cart);
        assertTrue(resultOrder.equals(order));

    }

}
