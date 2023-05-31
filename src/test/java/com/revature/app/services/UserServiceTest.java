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

/**
 * The UserServiceTest class, it handles all testing of the UserService
 * 
 */
public class UserServiceTest {

    /**
     * Here we mock our UserDAO and UserService
     * in order to test their associated methods
     */
    @Mock
    private UserDAO userDao;

    private UserService userService;

    /**
     * setUp creates the environment for tests to be run
     */
    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        userService = new UserService(userDao);
    }

    /**
     * testRegister tests the functionality of
     * the register method to create new Users
     */
    @Test
    public void testRegister() {
        // Define the test input values
        String username = "testUser12";
        String password = "testPassword12";

        doNothing().when(userDao).save(any(User.class));
        User result = userService.register(username, password);
        verify(userDao, times(1)).save(any(User.class));
        assertEquals(username, result.getUsername());
    }

    /**
     * testisUniqueUsername tests a service method in userService
     * responsible for making sure that a new user does not share
     * the same username as an old user in the database
     */
    @Test
    public void testisUniqueUsername() {
        // Define test input values
        String username = "testUser12";
        String username2 = "testUser11";

        when(userDao.findByUsername(username)).thenReturn(Optional.of(new User()));
        when(userDao.findByUsername(username2)).thenReturn(Optional.empty());

        assertFalse(userService.isUniqueUsername(username));
        assertTrue(userService.isUniqueUsername(username2));

    }

    /**
     * testLogin tests the functionality of the login method in userService,
     * it takes the test values and confirms that login will return an Optional
     * user.
     */
    @Test
    public void testLogin() {
        // Test input values
        String id = "1";
        String username = "joker11";
        String password = "batman11";
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        User user = new User(id, username, hashed);

        when(userService.login(username, hashed)).thenReturn(Optional.of(user));
        Optional<User> returnedUser = userService.login("joker11", "batman11");
        assertTrue(returnedUser.equals(Optional.of(user)));
    }

    /**
     * testGetUserId makes sure that the userService method
     * findbyId is capable of returning a User object that
     * has a matching id to the one that is passed into findById
     * as a parameter.
     */
    @Test
    public void testGetUserId() {
        // Test input values
        String id = "1";
        String username = "testname11";
        String password = "password12";

        User user = new User(id, username, password);
        when(userService.findById("1")).thenReturn(user);
        User returnedUser = userService.findById("1");
        assertTrue(returnedUser.equals(user));
    }
}