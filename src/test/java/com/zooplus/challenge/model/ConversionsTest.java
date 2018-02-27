package com.zooplus.challenge.model;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class ConversionsTest {

    @Test
    public void builderTest() {
        Conversions conversions = new Conversions.Builder("abc1").fromCurrency("USD")
                .toCurrency("INR").fromAmount(new BigDecimal(10)).toAmount(new BigDecimal(650))
                .forex(new BigDecimal(65)).build();
        assertEquals("userId", "abc1", conversions.getUserId());
        assertEquals("fromCurrency", "USD", conversions.getFromCurrency());
        assertEquals("toCurrency", "INR", conversions.getToCurrency());
        assertEquals("fromAmount", new BigDecimal(10), conversions.getFromAmount());
        assertEquals("toAmount", new BigDecimal(650), conversions.getToAmount());
        assertEquals("forex", new BigDecimal(65), conversions.getForex());
    }

}