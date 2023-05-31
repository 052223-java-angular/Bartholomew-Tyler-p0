package com.revature.app.utils;

import com.revature.app.models.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The session class allows user data to be carried throughout the program
 * without unnessary sql calls
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Session {
    private String id;
    private String username;

    // setSession takes the user's id and username and holds onto them in a Session
    // object
    public void setSession(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }

    // clearSession clears the session variables, this is done when a user logs out
    public void clearSession() {
        this.id = "";
        this.username = "";
    }
}
