package com.revature.app.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;

import com.revature.app.daos.UserDAO;
import com.revature.app.models.User;

import lombok.AllArgsConstructor;
/**
* The UserService class handles all of the business logic that goes
* into creating new users, allowing them to log in, etc.
*/
@AllArgsConstructor
public class UserService {
    private final UserDAO userDAO;
    // method that handles registration by taking in @param username and @param password, returns the newUser
    public User register(String username, String password) {
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        User newUser = new User(username, hashed);
        userDAO.save(newUser);
        return newUser;
    }
    /**
    * isUniqueUsername takes in a username and uses the userDAO method findByUsername,
    * which returns an complete Optional User if the user exists and
    * an empty one if it does not
    * @param username The username that is being checked 
    */
    public boolean isUniqueUsername(String username) {
        Optional<User> userOpt = userDAO.findByUsername(username);

        return userOpt.isEmpty();
    }
    /**
    * login takes in a username and a password and attempts to log in. If successful
    * it returns the user, otherwise it passes along an empty Optional user
    * @param username inputted username
    * @param password inputted password
    */
    public Optional<User> login(String username, String password) {

        Optional<User> user = userDAO.findByUsername(username);
        if (user.isEmpty()) {
            return Optional.empty();
        }
        boolean isMatching = BCrypt.checkpw(password, user.get().getPassword());
        if (isMatching) {
            return user;
        } else {
            return Optional.empty();
        }
    }
    /**
     * findById uses the findById method in userDAO to search for a user by their id
     * @param userId userId that is being checked
     * @return user 
     */
    public User findById(String userId) {
        return userDAO.findById(userId);
    }
}
