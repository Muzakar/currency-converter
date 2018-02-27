package com.zooplus.challenge.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Stores all the data related to conversions carried out by users.
 */
@Entity
@Table(name="CONVERSIONS")
public class Conversions implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private String userId;
    private String fromCurrency;
    private String toCurrency;
    private BigDecimal fromAmount;
    private BigDecimal toAmount;
    private BigDecimal forex;
    private Date time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public BigDecimal getFromAmount() {
        return fromAmount;
    }

    public void setFromAmount(BigDecimal fromAmount) {
        this.fromAmount = fromAmount;
    }

    public BigDecimal getToAmount() {
        return toAmount;
    }

    public void setToAmount(BigDecimal toAmount) {
        this.toAmount = toAmount;
    }

    public BigDecimal getForex() {
        return forex;
    }

    public void setForex(BigDecimal forex) {
        this.forex = forex;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Conversions{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", fromCurrency='" + fromCurrency + '\'' +
                ", toCurrency='" + toCurrency + '\'' +
                ", fromAmount=" + fromAmount +
                ", toAmount=" + toAmount +
                ", forex=" + forex +
                ", time=" + time +
                '}';
    }

    public static class Builder {
        private String userId;
        private String fromCurrency;
        private String toCurrency;
        private BigDecimal fromAmount;
        private BigDecimal toAmount;
        private BigDecimal forex;
        private Date time;

        public Builder(String userId) {
            this.userId = userId;
            this.time = new Date(System.currentTimeMillis());
        }

        public Builder fromCurrency (String fromCurrency) {
            this.fromCurrency = fromCurrency;
            return this;
        }

        public Builder fromAmount (BigDecimal fromAmount) {
            this.fromAmount = fromAmount;
            return this;
        }

        public Builder toCurrency (String toCurrency){
            this.toCurrency = toCurrency;
            return this;
        }

        public Builder toAmount (BigDecimal toAmount) {
            this.toAmount = toAmount;
            return this;
        }

        public Builder forex (BigDecimal forex) {
            this.forex = forex;
            return this;
        }

        public Conversions build() {
            Conversions conversions = new Conversions();
            conversions.setUserId(userId);
            conversions.setForex(forex);
            conversions.setFromAmount(fromAmount);
            conversions.setFromCurrency(fromCurrency);
            conversions.setToAmount(toAmount);
            conversions.setToCurrency(toCurrency);
            conversions.setTime(time);
            return conversions;
        }

    }

}
