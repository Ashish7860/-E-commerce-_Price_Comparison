package com.nagarro.backend_server_wallmart.controller;

import com.nagarro.backend_server_walmart.controller.DealController;
import com.nagarro.backend_server_walmart.dto.CategoryDealsResponse;
import com.nagarro.backend_server_walmart.exception.ItemNotFoundException;
import com.nagarro.backend_server_walmart.model.DealItem;
import com.nagarro.backend_server_walmart.service.DealService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;

@SpringJUnitConfig
public class DealControllerTest {

    @Mock
    private DealService dealService;

    @InjectMocks
    private DealController dealController;

    @BeforeEach
    void setUp() {
        // Initialize Mockito annotations
        MockitoAnnotations.openMocks(this);
    }

    // Test: Valid category "jeans"
    @Test
    void testGetDeals_ValidCategory() {
        // Mocking a valid response
        CategoryDealsResponse mockResponse = new CategoryDealsResponse();
        mockResponse.setCategoryName("jeans");
        mockResponse.setDealItems(createMockDeals());

        when(dealService.getDealsByCategory("jeans")).thenReturn(mockResponse);

        // Call the controller method
        ResponseEntity<CategoryDealsResponse> response = dealController.getDeals("jeans");

        // Verify response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("jeans", response.getBody().getCategoryName());
        assertEquals(3, response.getBody().getDealItems().size());
    }

    // Test: Invalid category scenario
    @Test
    void testGetDeals_InvalidCategory() {
        // Mocking the exception for an invalid category
        when(dealService.getDealsByCategory(anyString()))
                .thenThrow(new ItemNotFoundException("No deals found for the category: electronics"));

        try {
            // Call the controller method with invalid category
            dealController.getDeals("electronics");
        } catch (ItemNotFoundException ex) {
            // Verify exception message
            assertEquals("No deals found for the category: electronics", ex.getMessage());
        }
    }

    // Test: Empty category scenario
    @Test
    void testGetDeals_EmptyCategory() {
        // Mocking exception for empty category
        when(dealService.getDealsByCategory(anyString()))
                .thenThrow(new ItemNotFoundException("Category name cannot be null or empty."));

        try {
            // Call the controller method with empty category
            dealController.getDeals("");
        } catch (ItemNotFoundException ex) {
            // Verify exception message
            assertEquals("Category name cannot be null or empty.", ex.getMessage());
        }
    }

    // Test: Null category scenario
    @Test
    void testGetDeals_NullCategory() {
        // Mocking exception for null category
        when(dealService.getDealsByCategory(null))
                .thenThrow(new ItemNotFoundException("Category name cannot be null or empty."));

        try {
            // Call the controller method with null category
            dealController.getDeals(null);
        } catch (ItemNotFoundException ex) {
            // Verify exception message
            assertEquals("Category name cannot be null or empty.", ex.getMessage());
        }
    }

    // Helper method to create mock deal items
    private List<DealItem> createMockDeals() {
        List<DealItem> mockDeals = new ArrayList<>();
        mockDeals.add(new DealItem("777654321", "Men Black Regular Jeans", "32", "H&M",
            "https://i.ebayimg.com/images/g/~**********N/s-l225.jpg", 29.99, 40.0, 11.99,
            "LIST_PRICE", 17.99, 4, "2024-06-20T15:26:00Z", "2024-12-20T14:59:59Z"));

        mockDeals.add(new DealItem("67A-8", "Black Men Slim Fit Mid Rise Jeans", "30", "Louis Philippe Jeans",
            "https://images-na.ssl-images-amazon.com/images/I/81nCNSxZcbL._AC_UX679_.jpg",
            29.99, 40.0, 11.99, "LIST_PRICE", 17.99, 8, "2024-09-20T15:26:00Z", "2024-10-20T14:59:59Z"));

        mockDeals.add(new DealItem("55666778231", "Light Fade Clean Look Stretchable Jeans", "32", "Louis Philippe Jeans",
            "https://i.ebayimg.com/images/g/~**********N/s-l225.jpg", 39.99, 25.0, 9.99,
            "LIST_PRICE", 29.99, 3, "2024-06-20T15:26:00Z", "2024-12-20T14:59:59Z"));

        return mockDeals;
    }
}
