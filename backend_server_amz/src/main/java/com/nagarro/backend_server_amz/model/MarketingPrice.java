package com.nagarro.backend_server_amz.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;

@Embeddable
public class MarketingPrice {
    @AttributeOverrides({
        @AttributeOverride(name = "value", column = @Column(name = "original_price_value")),
        @AttributeOverride(name = "currency", column = @Column(name = "original_price_currency"))
    })
    private OriginalPrice originalPrice;
    
    private double discountPercentage;
    
    @AttributeOverrides({
        @AttributeOverride(name = "value", column = @Column(name = "discount_amount_value")),
        @AttributeOverride(name = "currency", column = @Column(name = "discount_amount_currency"))
    })
    private DiscountAmount discountAmount;
    
    private String priceTreatment;

	public OriginalPrice getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(OriginalPrice originalPrice) {
		this.originalPrice = originalPrice;
	}

	public double getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public DiscountAmount getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(DiscountAmount discountAmount) {
		this.discountAmount = discountAmount;
	}

	public String getPriceTreatment() {
		return priceTreatment;
	}

	public void setPriceTreatment(String priceTreatment) {
		this.priceTreatment = priceTreatment;
	}

}