package com.revature.app.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The Cart class acts as a model for a user's Cart:
 * 
 * @param id           Acts as the primary key and identifier
 * @param user         Represents the user that the cart belongs to
 * @param cartproducts Represenets the products in the cart
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Cart {
    private String id;
    private User user;
    private List<CartProduct> cartProducts;
}
