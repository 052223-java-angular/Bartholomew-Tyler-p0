package com.revature.app.models;

import java.util.Currency;
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
    private Currency amount;
    private String payment_method_id;
}
