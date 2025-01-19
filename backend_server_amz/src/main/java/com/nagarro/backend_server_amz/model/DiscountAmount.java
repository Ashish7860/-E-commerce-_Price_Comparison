package com.nagarro.backend_server_amz.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class DiscountAmount {
    private double value; // Assuming it has a value field
    private String currency; // Assuming it has a currency field
    
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
