package tests;
import exceptions.GenericException;
import exceptions.auth.AlreadyLoggedInException;
import exceptions.auth.UserAlreadyExists;
import exceptions.auth.UserNotFoundException;

import org.junit.Before;
import org.junit.Test;

import auth.Authenticator;

import static org.junit.Assert.*;

public class AuthenticatorTest {

    private Authenticator authenticator;

    @Before
    public void setUp() {
        authenticator = new Authenticator();
    }

    @Test
    public void testCreateUser_Success() throws UserAlreadyExists, GenericException {
        authenticator.createUser("newuser@example.com", "password123", "123 Test St");
    }

    @Test(expected = UserAlreadyExists.class)
    public void testCreateUser_UserAlreadyExists() throws UserAlreadyExists, GenericException {
        authenticator.createUser("existinguser@example.com", "password123", "123 Test St");
        authenticator.createUser("existinguser@example.com", "password123", "123 Test St");
    }

    @Test
    public void testLogin_Success() throws UserNotFoundException, AlreadyLoggedInException, UserAlreadyExists, GenericException {
        authenticator.createUser("testuser@example.com", "password123", "123 Test St");
        authenticator.login("testuser@example.com", "password123");
        assertTrue(authenticator.isLoggedIn());
        assertNotNull(authenticator.getCurrentUser());
        assertEquals("testuser@example.com", authenticator.getCurrentUser().getEmail());
    }

    @Test(expected = UserNotFoundException.class)
    public void testLogin_UserNotFound() throws UserNotFoundException, AlreadyLoggedInException {
        authenticator.login("nonexistentuser@example.com", "wrongpassword");
    }

    @Test(expected = AlreadyLoggedInException.class)
    public void testLogin_AlreadyLoggedIn() throws UserAlreadyExists, AlreadyLoggedInException, UserNotFoundException, GenericException {
        authenticator.createUser("testuser2@example.com", "password123", "123 Test St");
        authenticator.login("testuser2@example.com", "password123");
        authenticator.login("testuser2@example.com", "password123"); // Should throw AlreadyLoggedInException
    }

    @Test
    public void testLogout() throws UserAlreadyExists, AlreadyLoggedInException, UserNotFoundException, GenericException {
        authenticator.createUser("logoutuser@example.com", "password123", "123 Test St");
        authenticator.login("logoutuser@example.com", "password123");
        authenticator.logout();
        assertFalse(authenticator.isLoggedIn());
        assertNull(authenticator.getCurrentUser());
    }

    @Test
    public void testIsLoggedIn_WhenNotLoggedIn() {
        assertFalse(authenticator.isLoggedIn());
    }
}
