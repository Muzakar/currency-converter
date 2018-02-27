package com.zooplus.challenge.dao;

import com.zooplus.challenge.model.Conversions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import yahoofinance.YahooFinance;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Component
public class ForexDAO {

    private static final Logger logger = LogManager.getLogger();

    @PersistenceContext
    private EntityManager em;

    public BigDecimal fetchCurrentForex(String fromCurrency, String toCurrency) throws IOException {
        return YahooFinance.getFx(fromCurrency + toCurrency + "=X").getPrice();
    }

    @Transactional
    public void persist(Conversions conversions) {
        em.persist(conversions);
        logger.info("Persisted Conversion - [{}]", conversions);
    }

    @Transactional
    public List<Conversions> getConversionsForUser(String userId) {
        return executeQuery("SELECT c FROM Conversions c WHERE c.userId='" + userId + "' ORDER BY c.id DESC");
    }

    public List<Conversions> executeQuery(String queryString) {
        logger.info("Executing query - [{}]", queryString);
        TypedQuery<Conversions> query = em.createQuery(queryString, Conversions.class);
        logger.info("Query completed - [{}]", queryString);
        return query.getResultList();
    }

}
