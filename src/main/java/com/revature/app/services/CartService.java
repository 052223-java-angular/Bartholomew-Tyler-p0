package com.revature.app.services;

import com.revature.app.daos.CartDAO;
import com.revature.app.models.Cart;
import com.revature.app.models.User;

import lombok.AllArgsConstructor;
/**
* The CartService class handles the buisness logic behind actions regarding 
* the cart
*/
@AllArgsConstructor
public class CartService {
    private final CartDAO cartDAO;
    // Creates a new cart associated with it's user through their user id 
    public void createCartFromUserId(String userId) {
        cartDAO.save(new Cart(null, new User(userId, "", ""), null));
    }
    // Gets the cart that is associated with the user by passing in their user id as a parameter
    public Cart getCartFromUserId(String userId) {
        return cartDAO.getCartFromUserId(userId);
    }
    // Adds individual products into the cart, making sure to keep track of the quantity to create cartproducts later
    public void addProductToCart(String cartId, String productId, int quantity) {
        cartDAO.addProductToCart(cartId, productId, quantity);
    }
    //Removes a product from a cart by passing in the cart's id and the product id of the item in question
    public void removeProductFromCart(String cartId, String productId) {
        cartDAO.removeProductFromCart(cartId, productId);
    }
    //Gives the user the option to change the amount of any particular item that exists within the cart
    public void updateQuantityOfProduct(String cartProductId, int updatedQuantity) {
        cartDAO.updateQuantityOfProduct(cartProductId, updatedQuantity);
    }
}
