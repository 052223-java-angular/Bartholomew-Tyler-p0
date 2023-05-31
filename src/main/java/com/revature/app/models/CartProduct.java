package com.revature.app.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
    /**
    * The CartProduct class acts as a model for the products in the cart:
    *@param id  Acts as the primary key and identifier
    *@param cartId Represents the id of the cart that the cartProduct belongs to
    *@param product Represents the kind of product that it is
    *@param quantity The amount of these products that are in the cart
    */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CartProduct {
    private String id;
    private String cartId;
    private Product product;
    private int quantity;
}
