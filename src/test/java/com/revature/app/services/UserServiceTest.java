package com.revature.app.services;

import org.junit.Before;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import com.revature.app.models.User;
import com.revature.app.daos.UserDAO;


public class UserServiceTest {
    
    @Mock
    private UserDAO userDao;

    private UserService userService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        userService = new UserService(userDao);
    }

    @Test
    public void testRegister() {
        // Define the test input values
        String username = "testUser12";
        String password = "testPassword12";
        // User user = new User(username, BCrypt.hashpw(password, BCrypt.gensalt()));

        doNothing().when(userDao).save(any(User.class));
        User result = userService.register(username, password);
        verify(userDao, times(1)).save(any(User.class));
        assertEquals(username, result.getUsername());
    }

    @Test
    public void testisUniqueUsername() {
        String username = "testUser12";
        String password = "password11";
        String username2 = "testUser12";
        
        userService.register(username, password);
    
        assertFalse(userService.isUniqueUsername(username2));

    }

    @Test
        public void testFindById() {

        }


}
