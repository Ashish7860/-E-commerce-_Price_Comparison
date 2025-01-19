package com.nagarro.ecommers.model;

import java.time.LocalDate;

public class DealItem {
    private String itemId;
    private String productTitle;
    private String size;
    private String brand;
    private String imageUrl;
    private OriginalPrice price;  // Assuming this class has a getPrice() method
    private MarketingPrice marketingPrice; // Assuming this class has a getPrice() method
    private int stock;
    private LocalDate dealStartDate;
    private LocalDate dealEndDate;

    // Getters and Setters

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

    public OriginalPrice getPrice() {
        return price;
    }

    public void setPrice(OriginalPrice price) {
        this.price = price;
    }

    public MarketingPrice getMarketingPrice() {
        return marketingPrice;
    }

    public void setMarketingPrice(MarketingPrice marketingPrice) {
        this.marketingPrice = marketingPrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public LocalDate getDealStartDate() {
        return dealStartDate;
    }

    public void setDealStartDate(LocalDate dealStartDate) {
        this.dealStartDate = dealStartDate;
    }

    public LocalDate getDealEndDate() {
        return dealEndDate;
    }

    public void setDealEndDate(LocalDate dealEndDate) {
        this.dealEndDate = dealEndDate;
    }

    // Method to calculate the discount amount
    public double getDiscountAmount() {
        // Assuming OriginalPrice and MarketingPrice have 'getPrice()' method returning double
        if (price == null || marketingPrice == null) {
            return 0; 
        }
        double originalPriceValue = price.getPrice();
        double marketingPriceValue = marketingPrice.getPrice();

        // Return the discount amount
        return originalPriceValue - marketingPriceValue;
    }
}
