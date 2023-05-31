package com.revature.app.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.mindrot.jbcrypt.BCrypt;
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
    //Test to see if Registration creates a new User
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
    //Test to see if the uniqueness of usernames can be preserved
    @Test
    public void testisUniqueUsername() {
        String username = "testUser12";
        String username2 = "testUser11";
        
        when(userDao.findByUsername(username)).thenReturn(Optional.of(new User()));
        when(userDao.findByUsername(username2)).thenReturn(Optional.empty());
    
        assertFalse(userService.isUniqueUsername(username));
        assertTrue(userService.isUniqueUsername(username2));

    }
    //Test to see that an unsuccessful login will return an empty user object
    @Test
        public void testLogin() {
            String id = "1";
            String username = "joker11";
            String password = "batman11";
            String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
            User user = new User(id, username, hashed);
            when(userService.login(username, hashed)).thenReturn(Optional.of(user));
            Optional<User> returnedUser = userService.login("joker11", "batman11");
            assertTrue(returnedUser.equals(Optional.of(user)));
        }

    @Test
        public void testGetUserId() {
            String id = "1";
            String username = "testname11";
            String password = "password12";

            User user = new User(id, username, password);
            when(userService.findById("1")).thenReturn(user);
            User returnedUser = userService.findById("1");
            assertTrue(returnedUser.equals(user));
        }
}
