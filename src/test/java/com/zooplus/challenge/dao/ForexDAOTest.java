package com.zooplus.challenge.dao;

import com.zooplus.challenge.model.Conversions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class ForexDAOTest extends AbstractDAOLayerTest {

    @Autowired
    private ForexDAO forexDAO;

    @Test
    public void persistSingleUserEntries() {

        List<Conversions> conversionsList1 = forexDAO.getConversionsForUser("abc1");
        assertEquals(0, conversionsList1.size());

        Conversions conversions1 = new Conversions.Builder("abc1").fromCurrency("USD")
                .toCurrency("INR").fromAmount(new BigDecimal(10)).toAmount(new BigDecimal(650))
                .forex(new BigDecimal(65)).build();
        forexDAO.persist(conversions1);

        List<Conversions> conversionsList2 = forexDAO.getConversionsForUser("abc1");
        assertEquals(1, conversionsList2.size());
        Conversions singleConversion = conversionsList2.get(0);
        assertEquals("userId", "abc1", singleConversion.getUserId());
        assertEquals("fromCurrency", "USD", singleConversion.getFromCurrency());
        assertEquals("toCurrency", "INR", singleConversion.getToCurrency());
        assertEquals("fromAmount", new BigDecimal(10), singleConversion.getFromAmount());
        assertEquals("toAmount", new BigDecimal(650), singleConversion.getToAmount());
        assertEquals("forex", new BigDecimal(65), singleConversion.getForex());


        Conversions conversions2 = new Conversions.Builder("abc1").fromCurrency("GBP")
                .toCurrency("INR").fromAmount(new BigDecimal(10)).toAmount(new BigDecimal(900))
                .forex(new BigDecimal(90)).build();
        forexDAO.persist(conversions2);

        List<Conversions> conversionsList3 = forexDAO.getConversionsForUser("abc1");
        assertEquals(2, conversionsList3.size());

        Conversions secondUpdate = forexDAO.executeQuery("SELECT c FROM Conversions c WHERE c.userId='abc1' AND c.fromCurrency='GBP'", 1).get(0);
        assertEquals(new BigDecimal(10), secondUpdate.getFromAmount());
        assertEquals(new BigDecimal(900), secondUpdate.getToAmount());
        assertEquals(new BigDecimal(90), secondUpdate.getForex());
    }

    @Test
    public void persistMultipleUsers() {

        Conversions conversions1 = new Conversions.Builder("abc1").fromCurrency("USD")
                .toCurrency("INR").fromAmount(new BigDecimal(10)).toAmount(new BigDecimal(650))
                .forex(new BigDecimal(65)).build();
        forexDAO.persist(conversions1);

        Conversions conversions2 = new Conversions.Builder("abc2").fromCurrency("GBP")
                .toCurrency("INR").fromAmount(new BigDecimal(10)).toAmount(new BigDecimal(900))
                .forex(new BigDecimal(90)).build();
        forexDAO.persist(conversions2);

        Conversions conversions3 = new Conversions.Builder("abc2").fromCurrency("SGP")
                .toCurrency("INR").fromAmount(new BigDecimal(10)).toAmount(new BigDecimal(630))
                .forex(new BigDecimal(63)).build();
        forexDAO.persist(conversions3);

        Conversions conversions4 = new Conversions.Builder("abc1").fromCurrency("AUD")
                .toCurrency("INR").fromAmount(new BigDecimal(10)).toAmount(new BigDecimal(700))
                .forex(new BigDecimal(70)).build();
        forexDAO.persist(conversions4);

        Conversions conversions5 = new Conversions.Builder("abc1").fromCurrency("JPY")
                .toCurrency("INR").fromAmount(new BigDecimal(100)).toAmount(new BigDecimal(200))
                .forex(new BigDecimal(2)).build();
        forexDAO.persist(conversions5);

        List<Conversions> conversionsList1 = forexDAO.getConversionsForUser("abc1");
        assertEquals(3, conversionsList1.size());

        List<Conversions> conversionsList2 = forexDAO.getConversionsForUser("abc2");
        assertEquals(2, conversionsList2.size());

    }

    @Test
    public void testOrdering() {

        Conversions conversions1 = new Conversions.Builder("abc1").fromCurrency("1")
                .toCurrency("1").fromAmount(new BigDecimal(10)).toAmount(new BigDecimal(20))
                .forex(new BigDecimal(2)).build();
        forexDAO.persist(conversions1);

        Conversions conversions2 = new Conversions.Builder("abc1").fromCurrency("2")
                .toCurrency("2").fromAmount(new BigDecimal(10)).toAmount(new BigDecimal(20))
                .forex(new BigDecimal(2)).build();
        forexDAO.persist(conversions2);

        Conversions conversions3 = new Conversions.Builder("abc1").fromCurrency("3")
                .toCurrency("3").fromAmount(new BigDecimal(10)).toAmount(new BigDecimal(20))
                .forex(new BigDecimal(2)).build();
        forexDAO.persist(conversions3);

        List<Conversions> conversionsList = forexDAO.getConversionsForUser("abc1");
        assertEquals(3, conversionsList.size());

        int count = 3;
        for (Conversions conversions : conversionsList) {
            assertEquals("" + count, conversions.getToCurrency());
            assertEquals("" + count, conversions.getFromCurrency());
            count--;
        }

    }

    @Test
    public void getTop10Conversions() {

        IntStream.range(0, 15).forEach(count -> forexDAO.persist(new Conversions.Builder("abc1").fromCurrency("USD")
                .toCurrency("INR").fromAmount(new BigDecimal(10)).toAmount(new BigDecimal(650))
                .forex(new BigDecimal(65)).build()));

        IntStream.range(0, 15).forEach(count -> forexDAO.persist(new Conversions.Builder("abc2").fromCurrency("GBP")
                .toCurrency("INR").fromAmount(new BigDecimal(10)).toAmount(new BigDecimal(900))
                .forex(new BigDecimal(90)).build()));

        List<Conversions> conversions1 = forexDAO.getConversionsForUser("abc1");
        assertEquals(10, conversions1.size());
        int count = 15;
        for(Conversions conversions : conversions1){
            assertEquals(count, conversions.getId());
            count--;
        }

        List<Conversions> conversions2 = forexDAO.getConversionsForUser("abc2");
        assertEquals(10, conversions2.size());
        int count2 = 30;
        for(Conversions conversions : conversions2){
            assertEquals(count2, conversions.getId());
            count2--;
        }

    }

}