package com.revature.app.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.app.daos.CartDAO;
import com.revature.app.models.Cart;
import com.revature.app.models.User;

public class CartServiceTest {

    @Mock
    private CartDAO cartDAO;

    private CartService cartService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        cartService = new CartService(cartDAO);
    }

    @Test
    public void testCreateCartFromUserId() {
        String userId = UUID.randomUUID().toString();

        doNothing().when(cartDAO).save(any(Cart.class));
        cartService.createCartFromUserId(userId);
        verify(cartDAO, times(1)).save(any(Cart.class));
    }

    @Test
    public void testGetCartFromUserId() {
        User sampleUser = new User("test", "test");
        Cart sampleCart = new Cart(UUID.randomUUID().toString(), sampleUser, null);
        when(cartDAO.getCartFromUserId(sampleUser.getId())).thenReturn(sampleCart);

        Cart returnCart = cartService.getCartFromUserId(sampleUser.getId());
        assertEquals(returnCart.getUser().getId(), sampleUser.getId());
    }

    @Test
    public void testAddProductToCart() {
        String cartId = UUID.randomUUID().toString();
        String productId = UUID.randomUUID().toString();
        int quantity = 1;

        doNothing().when(cartDAO).addProductToCart(any(String.class), any(String.class), any(Integer.class));
        cartService.addProductToCart(cartId, productId, quantity);
        verify(cartDAO, times(1)).addProductToCart(any(String.class), any(String.class), any(Integer.class));
    }

    @Test
    public void testRemoveProductFromCart() {
        String cartId = UUID.randomUUID().toString();
        String productId = UUID.randomUUID().toString();
        doNothing().when(cartDAO).removeProductFromCart(any(String.class), any(String.class));
        cartService.removeProductFromCart(cartId, productId);
        verify(cartDAO, times(1)).removeProductFromCart(any(String.class), any(String.class));
    }

    @Test
    public void testUpdateQuantityOfProduct() {
        String cartProductId = UUID.randomUUID().toString();
        int updatedQuantity = 2;
        doNothing().when(cartDAO).updateQuantityOfProduct(any(String.class), any(Integer.class));
        cartService.updateQuantityOfProduct(cartProductId, updatedQuantity);
        verify(cartDAO, times(1)).updateQuantityOfProduct(any(String.class), any(Integer.class));
    }
}
