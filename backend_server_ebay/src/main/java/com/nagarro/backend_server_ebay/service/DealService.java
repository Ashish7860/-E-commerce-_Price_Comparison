package com.nagarro.backend_server_ebay.service;

import com.nagarro.backend_server_ebay.dto.CategoryDealsResponse;
import com.nagarro.backend_server_ebay.exception.ItemNotFoundException;
import com.nagarro.backend_server_ebay.model.DealItem;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DealService {

    // Stores the deals by category name
    private final Map<String, List<DealItem>> categoryDealsMap = new HashMap<>();

    @PostConstruct
    public void initializeDeals() {
        // Initialize with "jeans" category and its respective deals
        addCategoryWithItems("jeans", createJeansDeals());
    }

    /**
     * Retrieves deals by the given category name.
     *
     * @param categoryName the name of the category.
     * @return the list of deals for the category.
     * @throws ItemNotFoundException if no deals are found for the category.
     */
    public CategoryDealsResponse getDealsByCategory(String categoryName) {
        // Check for null or empty category names
        if (categoryName == null || categoryName.trim().isEmpty()) {
            throw new ItemNotFoundException("Category name cannot be null or empty.");
        }

        // Fetch the deals for the provided category (case-insensitive)
        List<DealItem> dealItems = categoryDealsMap.get(categoryName.toLowerCase());

        // Throw exception if no deals are found
        if (dealItems == null || dealItems.isEmpty()) {
            throw new ItemNotFoundException("No deals found for the category: " + categoryName);
        }

        // Prepare and return the response
        CategoryDealsResponse response = new CategoryDealsResponse();
        response.setCategoryName(categoryName);
        response.setDealItems(dealItems);
        return response;
    }

    /**
     * Adds a category and its items to the internal map.
     *
     * @param category the category name.
     * @param items    the list of deal items for the category.
     */
    private void addCategoryWithItems(String category, List<DealItem> items) {
        categoryDealsMap.put(category.toLowerCase(), items);
    }

    /**
     * Creates a list of jeans deals.
     *
     * @return the list of jeans deals.
     */
    private List<DealItem> createJeansDeals() {
        List<DealItem> jeanDeals = new ArrayList<>();

        jeanDeals.add(new DealItem("2345678767", "Blue American Eagle Jeans", "32", "American Eagle",
                "https://i.ebayimg.com/images/g/~**********N/s-l225.jpg", 49.99, 50.0, 24.99,
                "LIST_PRICE", 24.99, 4, "2023-06-20T15:26:00Z", "2023-12-20T14:59:59Z"));
        

        jeanDeals.add(new DealItem("4567822189", "Navy Blue Rare Rabbit Jeans", "32", "Rare Rabbit",
                "https://i.ebayimg.com/images/g/~**********N/s-l225.jpg", 39.99, 25.0, 9.99,
                "LIST_PRICE", 29.99, 0, "2024-06-20T15:26:00Z", "2024-12-20T14:59:59Z"));

        jeanDeals.add(new DealItem("67A-8", "Black Men Slim Fit Mid Rise Jeans", "30", "Louis Philippe Jeans",
                "https://images-na.ssl-images-amazon.com/images/I/81nCNSxZcbL._AC_UX679_.jpg",
                29.99, 55.0, 16.49, "LIST_PRICE", 13.49, 0, "2024-09-20T15:26:00Z", "2024-11-20T14:59:59Z"));

        return jeanDeals;
    }
}
