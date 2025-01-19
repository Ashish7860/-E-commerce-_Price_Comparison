package com.nagarro.backend_server_walmart.model;


import jakarta.persistence.Embeddable;

@Embeddable
public class MarketingPrice {
    private double originalPrice;
    private double discountPercentage;
    private double discountAmount;
    private String priceTreatment;

    public MarketingPrice(double originalPrice, double discountPercentage, double discountAmount, String priceTreatment) {
        this.originalPrice = originalPrice;
        this.discountPercentage = discountPercentage;
        this.discountAmount = discountAmount;
        this.priceTreatment = priceTreatment;
    }

	public double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(double originalPrice) {
		this.originalPrice = originalPrice;
	}

	public double getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public String getPriceTreatment() {
		return priceTreatment;
	}

	public void setPriceTreatment(String priceTreatment) {
		this.priceTreatment = priceTreatment;
	}

    // Getters and Setters
}
