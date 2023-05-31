package com.revature.app.models;


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
//Unimplemented
public class PaymentMethod {
    private String id;
    private String number;
    private String cvc;
    private String user_id;
}
