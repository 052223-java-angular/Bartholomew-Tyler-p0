package com.revature.app.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The OrderProduct class acts as a model for a product in an order
 * 
 * @param id         Acts as the primary key and identifier
 * @param order_id   Represents the id of the order that the product is in
 * @param product_id Represenets the id of the type of product it is
 * @param quantity   Represents the amount of the product in the cart
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class OrderProduct {
    private String id;
    private String order_id;
    private String product_id;
    private int quantity;
}
