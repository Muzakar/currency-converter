package com.zooplus.challenge.services;

import com.zooplus.challenge.dao.ForexDAO;
import com.zooplus.challenge.model.Conversions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Service
public class CurrencyConverter {

    @Autowired
    private ForexDAO forexDAO;

    public BigDecimal convert(String userId, BigDecimal fromAmount, String fromCurrency, String toCurrency) throws IOException {
        BigDecimal fx = forexDAO.fetchCurrentForex(fromCurrency, toCurrency);
        BigDecimal toAmount = fromAmount.multiply(fx);
        Conversions conversions = new Conversions.Builder(userId)
                .forex(fx).fromAmount(fromAmount).fromCurrency(fromCurrency)
                .toAmount(toAmount).toCurrency(toCurrency).build();
        forexDAO.persist(conversions);
        return toAmount;
    }

    public List<Conversions> getAllConversionsForUser(String userId) {
        return forexDAO.getConversionsForUser(userId);
    }

}
