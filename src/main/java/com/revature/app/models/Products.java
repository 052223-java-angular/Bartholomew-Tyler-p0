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

public class Products {
    private String id;
    private String name;
    private String category;
    private Currency price; 
}
