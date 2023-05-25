package com.revature.app.utils;

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
    
    public void setSession(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }
}
