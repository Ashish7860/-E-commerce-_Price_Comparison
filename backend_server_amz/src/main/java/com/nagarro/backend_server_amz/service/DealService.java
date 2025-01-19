package com.nagarro.backend_server_amz.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.backend_server_amz.dto.CategoryDealsResponse;
import com.nagarro.backend_server_amz.model.DealItem;
import com.nagarro.backend_server_amz.model.DiscountAmount;
import com.nagarro.backend_server_amz.model.MarketingPrice;
import com.nagarro.backend_server_amz.model.OriginalPrice;
import com.nagarro.backend_server_amz.repository.DealItemRepository;

import jakarta.annotation.PostConstruct;

@Service
public class DealService {

    private final DealItemRepository dealItemRepository;
    private final Map<String, List<DealItem>> categoryDealsMap = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(DealService.class);

    @Autowired
    public DealService(DealItemRepository dealItemRepository) {
        this.dealItemRepository = dealItemRepository;
        logger.info("DealItemRepository injected successfully");
    }

    @PostConstruct
    public void initializeDeals() {
        logger.info("Initializing deals...");
        addCategoryWithItems("jeans", createJeansDeals());
    }

    public Optional<CategoryDealsResponse> getDealsByCategory(String categoryName) {
        if (categoryName == null || categoryName.trim().isEmpty()) {
            logger.warn("Category name is null or empty");
            return Optional.empty();
        }

        List<DealItem> dealItems = categoryDealsMap.get(categoryName.toLowerCase());

        if (dealItems == null) {
            logger.warn("No deals found for category: {}", categoryName);
            return Optional.empty();
        }

        CategoryDealsResponse response = new CategoryDealsResponse();
        response.setCategoryName(categoryName);
        response.setDealItems(dealItems);

        return Optional.of(response);
    }

    private void addCategoryWithItems(String category, List<DealItem> items) {
        categoryDealsMap.put(category.toLowerCase(), items);
        try {
            dealItemRepository.saveAll(items); // Saving to the database
            logger.info("Saved {} items for category: {}", items.size(), category);
        } catch (Exception e) {
            logger.error("Error saving items: {}", e.getMessage());
        }
    }

    private List<DealItem> createJeansDeals() {
        List<DealItem> jeanDeals = new ArrayList<>();
        
        jeanDeals.add(createDealItem("A5987", "Blue Killer Jeans", "32", "Killer", 
            "https://images-na.ssl-images-amazon.com/images/I/71i2XhHU3pL._AC_UX679_.jpg", 
            29.99, 50.0, 5.0, 14.99, 5, 
            "2024-06-20T15:26:00Z", "2024-12-30T14:59:59Z"));
        
        jeanDeals.add(createDealItem("67A-8", "Black Men Slim Fit Mid Rise Jeans", "30", "Louis Philippe Jeans",
            "https://images-na.ssl-images-amazon.com/images/I/81nCNSxZcbL._AC_UX679_.jpg", 
            29.99, 50.0, 14.99, 14.99, 8, 
            "2023-09-20T15:26:00Z", "2023-11-20T14:59:59Z"));
        
        return jeanDeals;
    }

    private DealItem createDealItem(String itemId, String title, String size, String brand, String imageUrl,
                                     double originalValue, double discountPercentage, double discountAmountValue, 
                                     double finalPrice, int stock, String startDate, String endDate) {

        DealItem dealItem = new DealItem();
        dealItem.setItemId(itemId);
        dealItem.setProductTitle(title);
        dealItem.setSize(size);
        dealItem.setBrand(brand);
        dealItem.setImageUrl(imageUrl);
        dealItem.setMarketingPrice(createMarketingPrice(originalValue, "USD", discountPercentage, discountAmountValue));
        dealItem.setPrice(finalPrice);
        dealItem.setStock(stock);
        dealItem.setDealStartDate(startDate);
        dealItem.setDealEndDate(endDate);

        return dealItem;
    }

    private MarketingPrice createMarketingPrice(double originalValue, String currency, double discountPercentage, double discountAmountValue) {
        MarketingPrice marketingPrice = new MarketingPrice();

        OriginalPrice originalPrice = new OriginalPrice();
        originalPrice.setValue(originalValue);
        originalPrice.setCurrency(currency);

        DiscountAmount discountAmount = new DiscountAmount();
        discountAmount.setValue(discountAmountValue);
        discountAmount.setCurrency(currency);

        marketingPrice.setOriginalPrice(originalPrice);
        marketingPrice.setDiscountPercentage(discountPercentage);
        marketingPrice.setDiscountAmount(discountAmount);
        marketingPrice.setPriceTreatment("LIST_PRICE");

        return marketingPrice;
    }
}

