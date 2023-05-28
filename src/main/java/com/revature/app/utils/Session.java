package com.revature.app.utils;

import com.revature.app.models.Product;
import com.revature.app.models.User;

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
public class Session {
    private String id;
    private String username;
    private String productId;

    public void setSession(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }

    public void setSessionProduct(Product product) {
        this.productId = product.getId();
    }

    public void clearSession() {
        this.id = "";
        this.username = "";
        this.productId = "";
    }

    public void clearProductSession() {
        this.productId = "";
    }
}
