package com.zooplus.challenge.dao;

import com.zooplus.challenge.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class UserDAO {

    private static final Logger logger = LogManager.getLogger();

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void addUser(User user){
        em.persist(user);
    }

    @Transactional
    public void updateUser(User user) {
        em.persist(user);
    }

    @Transactional
    public void deactivateUser(String userId) {
        User user = findUser(userId);
        user.setActive(false);
        em.persist(user);
    }

    @Transactional
    public User findUser(String userId) {
        User user = em.getReference(User.class, userId);
        logger.info("User retrieved :: " + user);
        return user;
    }

}
