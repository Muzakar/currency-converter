package com.zooplus.challenge.controller;

import com.zooplus.challenge.dao.ForexDAO;
import com.zooplus.challenge.dao.UserDAO;
import com.zooplus.challenge.helpers.HelperUtil;
import com.zooplus.challenge.services.CurrencyConverter;
import com.zooplus.challenge.services.UserServices;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Set;

@Configuration
public class ServiceMockProvider {

    @Bean
    public UserServices userServices() {
        return Mockito.mock(UserServices.class);
    }

    @Bean
    public UserDAO userDAO() {
        return Mockito.mock(UserDAO.class);
    }

    @Bean
    public EntityManager entityManager() {
        return Mockito.mock(EntityManager.class);
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        return Mockito.mock(EntityManagerFactory.class);
    }

    @Bean
    public CurrencyConverter currencyConverter() {
        return Mockito.mock(CurrencyConverter.class);
    }

    @Bean
    public ForexDAO forexDAO() {
        return Mockito.mock(ForexDAO.class);
    }

    @Bean
    public Set<String> currencies() {
        return HelperUtil.getCurrencies();
    }

    @Bean
    public Set<String> countries() {
        return HelperUtil.getCountries();
    }
}
