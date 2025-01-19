package com.nagarro.backend_server_walmart.service;

import com.nagarro.backend_server_walmart.dto.CategoryDealsResponse;
import com.nagarro.backend_server_walmart.exception.ItemNotFoundException;
import com.nagarro.backend_server_walmart.model.DealItem;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DealService {
    private final Map<String, List<DealItem>> categoryDealsMap = new HashMap<>();

    @PostConstruct
    public void initializeDeals() {
        addCategoryWithItems("jeans", createJeansDeals());
    }

    public CategoryDealsResponse getDealsByCategory(String categoryName) {
        List<DealItem> dealItems = categoryDealsMap.get(categoryName.toLowerCase());
        if (dealItems == null || dealItems.isEmpty()) {
            throw new ItemNotFoundException("No deals found for the category: " + categoryName);
        }
        CategoryDealsResponse response = new CategoryDealsResponse();
        response.setCategoryName(categoryName);
        response.setDealItems(dealItems);
        return response;
    }

    private void addCategoryWithItems(String category, List<DealItem> items) {
        categoryDealsMap.put(category.toLowerCase(), items);
    }

    private List<DealItem> createJeansDeals() {
        List<DealItem> jeanDeals = new ArrayList<>();
        
        jeanDeals.add(new DealItem("777654321", "Men Black Regular Jeans", "32", "H&M",
            "https://i.ebayimg.com/images/g/~**********N/s-l225.jpg", 29.99, 40.0, 11.99,
            "LIST_PRICE", 17.99, 4, "2024-06-20T15:26:00Z", "2024-12-20T14:59:59Z"));
        
        jeanDeals.add(new DealItem("67A-8", "Black Men Slim Fit Mid Rise Jeans", "30", "Louis Philippe Jeans",
                "https://images-na.ssl-images-amazon.com/images/I/81nCNSxZcbL._AC_UX679_.jpg",
                29.99, 40.0, 11.99, "LIST_PRICE", 17.99, 8, "2024-09-20T15:26:00Z", "2024-10-20T14:59:59Z"));
        
        jeanDeals.add(new DealItem("55666778231", "Light Fade Clean Look Stretchable Jeans", "32", "Louis Philippe Jeans",
            "https://i.ebayimg.com/images/g/~**********N/s-l225.jpg", 39.99, 25.0, 9.99,
            "LIST_PRICE", 29.99, 3, "2024-06-20T15:26:00Z", "2024-12-20T14:59:59Z"));
        
        return jeanDeals;
    }
}