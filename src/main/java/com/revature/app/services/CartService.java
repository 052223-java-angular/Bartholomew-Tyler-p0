package com.revature.app.services;

import com.revature.app.daos.CartDAO;
import com.revature.app.models.Cart;
import com.revature.app.models.User;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CartService {
    private final CartDAO cartDAO;

    public void createCartFromUserId(String userId) {
        cartDAO.save(new Cart(null, new User(userId, "", ""), null));
    }

    public Cart getCartFromUserId(String userId) {
        return cartDAO.getCartFromUserId(userId);
    }
}
