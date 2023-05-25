package com.revature.app.services;

import java.util.Scanner;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;

import com.revature.app.daos.UserDAO;
import com.revature.app.models.User;

import lombok.AllArgsConstructor;
 
@AllArgsConstructor
public class UserService {
    private final UserDAO userDAO;

    public User register(String username, String password) {
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        User newUser = new User(username, hashed);
        userDAO.save(newUser);
        return newUser;
    }

    public User login(String username, String password) {
        userDAO.
    }
}
