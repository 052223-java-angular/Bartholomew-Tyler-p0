package com.revature.app.models;

import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
    /**
    * The User class acts as a model for a user.
    *@param id  Acts as the primary key and identifier
    *@param username The user's username
    *@param password The user's password
    */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    @NotNull
    private String id;
    @Size(min = 6, max = 20, message = "Username should be between 6 and 20 characters.")
    @Pattern(regexp = "^(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$", message = "Username should only contain alphanumeric characters and underscores (_).")
    private String username;
    @Size(min = 8, max = 20, message = "Password should be between 8 and 20 characters.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]+$", message = "Password should have at least one letter and one number.")
    private String password;

    public User(String username, String password) {
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
    }
}
