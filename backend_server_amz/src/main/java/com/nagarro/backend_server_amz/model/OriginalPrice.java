package com.nagarro.backend_server_amz.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class OriginalPrice {
    private double value;
    private String currency;
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
