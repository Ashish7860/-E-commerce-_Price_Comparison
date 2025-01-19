package com.nagarro.backend_server_ebay.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class DiscountAmount {
    private double value;
    private String currency;

    // Getters and Setters
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
