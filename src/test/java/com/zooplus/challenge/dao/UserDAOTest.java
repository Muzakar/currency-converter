package com.zooplus.challenge.dao;

import com.zooplus.challenge.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class UserDAOTest extends AbstractDAOLayerTest {

    @Autowired
    UserDAO userDAO;

    @Test
    public void testAddAndFindUser() {
        List<User> userListBeforeUsersAreAdded = userDAO.executeQuery("SELECT u FROM User u");
        assertEquals(0, userListBeforeUsersAreAdded.size());

        User user1 = new User.Builder("abc1").userName("ABC1").password("pass1").email("abc1@abc.com").active(true).build();
        userDAO.addUser(user1);

        User user2 = new User.Builder("abc2").userName("ABC2").password("pass2").email("abc2@abc.com").active(false).build();
        userDAO.addUser(user2);

        List<User> userListAfterUsersAreAdded = userDAO.executeQuery("SELECT u FROM User u");
        assertEquals(2, userListAfterUsersAreAdded.size());

        User userABC1 = userDAO.findUser("abc1");
        assertEquals("ABC1", userABC1.getUserName());
        assertEquals("pass1", userABC1.getPassword());
        assertEquals("abc1@abc.com", userABC1.getEmail());
        assertTrue(userABC1.isActive());

        User userABC2 = userDAO.findUser("abc2");
        assertEquals("ABC2", userABC2.getUserName());
        assertEquals("pass2", userABC2.getPassword());
        assertEquals("abc2@abc.com", userABC2.getEmail());
        assertFalse(userABC2.isActive());
    }

    @Test
    public void TestUpdateAndFindUser() {
        User user1 = new User.Builder("abc1").userName("ABC1").password("pass1").email("abc1@abc.com").active(true).build();
        userDAO.addUser(user1);

        User user2 = new User.Builder("abc2").userName("ABC2").password("pass2").email("abc2@abc.com").active(false).build();
        userDAO.addUser(user2);

        User userABC1 = userDAO.findUser("abc1");
        assertEquals("ABC1", userABC1.getUserName());
        assertEquals("pass1", userABC1.getPassword());
        assertEquals("abc1@abc.com", userABC1.getEmail());
        assertTrue(userABC1.isActive());

        User userABC2 = userDAO.findUser("abc2");
        assertEquals("ABC2", userABC2.getUserName());
        assertEquals("pass2", userABC2.getPassword());
        assertEquals("abc2@abc.com", userABC2.getEmail());
        assertFalse(userABC2.isActive());

        userABC1.setEmail("abc-abc1@abc.com");
        userDAO.updateUser(user1);

        User userABC1AfterUpdate = userDAO.findUser("abc1");
        assertEquals("ABC1", userABC1AfterUpdate.getUserName());
        assertEquals("pass1", userABC1AfterUpdate.getPassword());
        assertEquals("abc-abc1@abc.com", userABC1AfterUpdate.getEmail());
        assertTrue(userABC1AfterUpdate.isActive());

        User userABC2AfterABC1Update = userDAO.findUser("abc2");
        assertEquals("ABC2", userABC2AfterABC1Update.getUserName());
        assertEquals("pass2", userABC2AfterABC1Update.getPassword());
        assertEquals("abc2@abc.com", userABC2AfterABC1Update.getEmail());
        assertFalse(userABC2AfterABC1Update.isActive());
    }

    @Test
    public void testDeactivateAndFindUser() {
        User user1 = new User.Builder("abc1").userName("ABC1").password("pass1").email("abc1@abc.com").active(true).build();
        userDAO.addUser(user1);

        User userABC1 = userDAO.findUser("abc1");
        assertEquals("ABC1", userABC1.getUserName());
        assertEquals("pass1", userABC1.getPassword());
        assertEquals("abc1@abc.com", userABC1.getEmail());
        assertTrue(userABC1.isActive());

        userDAO.deactivateUser("abc1");
        User deactivatedUserABC1 = userDAO.findUser("abc1");
        assertEquals("ABC1", deactivatedUserABC1.getUserName());
        assertEquals("pass1", deactivatedUserABC1.getPassword());
        assertEquals("abc1@abc.com", deactivatedUserABC1.getEmail());
        assertFalse(deactivatedUserABC1.isActive());
    }

}