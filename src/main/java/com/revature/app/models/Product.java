package com.revature.app.models;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
    /**
    * The Product class acts as a model for a product
    *@param id  Acts as the primary key and identifier
    *@param name Represents the name of the product
    *@param category Represents the type of item the product is
    *@param price Represents the price of the product per unit
    *@param description A brief description of what the item is
    */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Product {
    private String id;
    private String name;
    private String category;
    private BigDecimal price;
    private String description;
}
