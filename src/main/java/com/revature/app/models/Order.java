package com.revature.app.models;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
