package com.nagarro.backend_server_walmart.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class DealItem {
    @Id
    private String itemId;
    private String productTitle;
    private String size;
    private String brand;
    private String imageUrl;

    @Embedded
    private MarketingPrice marketingPrice;

    private double price;
    private int stock;
    private String dealStartDate;
    private String dealEndDate;

    public DealItem(String itemId, String productTitle, String size, String brand, String imageUrl,
                    double originalPrice, double discountPercentage, double discountAmount,
                    String priceTreatment, double price, int stock, String dealStartDate, String dealEndDate) {
        this.itemId = itemId;
        this.productTitle = productTitle;
        this.size = size;
        this.brand = brand;
        this.imageUrl = imageUrl;
        this.marketingPrice = new MarketingPrice(originalPrice, discountPercentage, discountAmount, priceTreatment);
        this.price = price;
        this.stock = stock;
        this.dealStartDate = dealStartDate;
        this.dealEndDate = dealEndDate;
    }

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public MarketingPrice getMarketingPrice() {
		return marketingPrice;
	}

	public void setMarketingPrice(MarketingPrice marketingPrice) {
		this.marketingPrice = marketingPrice;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getDealStartDate() {
		return dealStartDate;
	}

	public void setDealStartDate(String dealStartDate) {
		this.dealStartDate = dealStartDate;
	}

	public String getDealEndDate() {
		return dealEndDate;
	}

	public void setDealEndDate(String dealEndDate) {
		this.dealEndDate = dealEndDate;
	}
    

    
}
