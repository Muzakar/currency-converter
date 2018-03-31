package com.mk.challenge.controller;

import com.mk.challenge.dao.ForexDAO;
import com.mk.challenge.dao.UserDAO;
import com.mk.challenge.services.CurrencyConverter;
import com.mk.challenge.services.UserServices;
import com.mk.challenge.helpers.HelperUtil;
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
