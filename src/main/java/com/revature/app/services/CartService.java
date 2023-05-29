package com.revature.app.services;

import com.revature.app.daos.CartDAO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CartService {
    private final CartDAO cartDAO;

    public void createCartFromUserId(String userId) {
        cartDAO.createCartFromUserId(userId);
    }
}
