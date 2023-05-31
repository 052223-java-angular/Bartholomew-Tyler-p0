package com.revature.app.models;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
    /**
    * The Order class acts as a model for a user's Order:
    *@param id  Acts as the primary key and identifier
    *@param user_id Represents the user's id that the order belongs to
    *@param amount Represents how much was paid for the order
    *@param date The date the order was placed
    *@param orderProducts The products in the order
    */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Order {
    private String id;
    private String user_id;
    private BigDecimal amount;
    private String date;
    private List<OrderProduct> orderProducts;
}
