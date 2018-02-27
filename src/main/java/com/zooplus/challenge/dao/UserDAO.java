package com.zooplus.challenge.dao;

import com.zooplus.challenge.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Data Access Object for {@link User} entity
 */
@Component
public class UserDAO {

    private static final Logger logger = LogManager.getLogger();

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void addUser(User user){
        em.persist(user);
        logger.info("User with userId: [{}] is persisted", user.getUserId());
    }

    @Transactional
    public void updateUser(User user) {
        em.merge(user);
        logger.info("User with userId: [{}] is updated", user.getUserId());
    }

    @Transactional
    public void deactivateUser(String userId) {
        User user = findUser(userId);
        user.setActive(false);
        em.merge(user);
        logger.info("Deactivated user with userId: [{}]", userId);
    }

    @Transactional
    public User findUser(String userId) {
        User user = em.getReference(User.class, userId);
        logger.info("User retrieved :: " + user);
        return user;
    }

    @Transactional
    public List<User> executeQuery(String queryString) {
        logger.info("Executing query - [{}]", queryString);
        TypedQuery<User> query = em.createQuery(queryString, User.class);
        logger.info("Query completed - [{}]", queryString);
        return query.getResultList();
    }

}
