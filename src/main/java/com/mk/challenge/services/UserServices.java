package com.mk.challenge.services;

import com.mk.challenge.dao.UserDAO;
import com.mk.challenge.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service layer for {@link User} maintenance.
 */
@Service
public class UserServices {

    @Autowired
    private UserDAO userDAO;

    public void registerUser(User user) {
        userDAO.addUser(user);
    }

    public void deactivateUser(String userId) {
        userDAO.deactivateUser(userId);
    }

    public void activateUser(String userId) {
        User user = userDAO.findUser(userId);
        userDAO.updateUser(user);
    }

    public boolean isUserIdAvailable(String userId) {
        User user;
        try {
            user = userDAO.findUser(userId);
        } catch (Exception e) {
            user = null;
        }
        return user == null;
    }

    public boolean isValidUser(String userId, String password) {
        User user;
        try {
            user = fetchUser(userId);
            return user.isActive() && user.getPassword().equals(password);
        } catch (Exception e) {
            return false;
        }
    }

    public User fetchUser(String userId) {
        return userDAO.findUser(userId);
    }

}
