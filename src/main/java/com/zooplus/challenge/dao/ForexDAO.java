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

    public BigDecimal fetchHistoricalForex() {
        return BigDecimal.ZERO;
    }

    @Transactional
    public void persist(Conversions conversions) {
        em.persist(conversions);
    }

    @Transactional
    public List<Conversions> getConversionsForUser(String userId) {
        String queryString = "SELECT c FROM Conversions c WHERE c.userId='" + userId + "' ORDER BY c.id";
        TypedQuery<Conversions> query = em.createQuery(queryString, Conversions.class);
        return query.getResultList();
    }

}
