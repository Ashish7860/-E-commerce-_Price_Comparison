package com.nagarro.backend_server_ebay.controller;

import com.nagarro.backend_server_ebay.dto.CategoryDealsResponse;
import com.nagarro.backend_server_ebay.exception.ItemNotFoundException;
import com.nagarro.backend_server_ebay.service.DealService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;

class DealControllerTest {

    @Mock
    private DealService dealService;

    @InjectMocks
    private DealController dealController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test: Success case for valid category "jeans"
    @Test
    void testGetDeals_ValidCategory() {
        // Mocking the response from service
        CategoryDealsResponse mockResponse = new CategoryDealsResponse();
        mockResponse.setCategoryName("jeans");
        mockResponse.setDealItems(new ArrayList<>());  // Empty deals, can add items

        when(dealService.getDealsByCategory("jeans")).thenReturn(mockResponse);

        // Call the controller method
        ResponseEntity<CategoryDealsResponse> response = dealController.getDeals("jeans");

        // Verify response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("jeans", response.getBody().getCategoryName());
    }

    // Test: Invalid category scenario - ItemNotFoundException is thrown
    @Test
    void testGetDeals_InvalidCategory() {
        // Mocking the exception for an invalid category
        when(dealService.getDealsByCategory(anyString()))
                .thenThrow(new ItemNotFoundException("No deals found for the category: electronics"));

        try {
            // Call the controller method with invalid category
            dealController.getDeals("electronics");
        } catch (ItemNotFoundException ex) {
            // Verify the exception message
            assertEquals("No deals found for the category: electronics", ex.getMessage());
        }
    }

    // Test: Empty category case - expect exception for empty category
    @Test
    void testGetDeals_EmptyCategory() {
        // Mocking the exception for empty category
        when(dealService.getDealsByCategory(anyString()))
                .thenThrow(new ItemNotFoundException("Category name cannot be null or empty."));

        try {
            // Call the controller method with an empty category
            dealController.getDeals("");
        } catch (ItemNotFoundException ex) {
            // Verify the exception message
            assertEquals("Category name cannot be null or empty.", ex.getMessage());
        }
    }

    // Test: Null category case - expect exception for null category
    @Test
    void testGetDeals_NullCategory() {
        // Mocking the exception for null category
        when(dealService.getDealsByCategory(null))
                .thenThrow(new ItemNotFoundException("Category name cannot be null or empty."));

        try {
            // Call the controller method with a null category
            dealController.getDeals(null);
        } catch (ItemNotFoundException ex) {
            // Verify the exception message
            assertEquals("Category name cannot be null or empty.", ex.getMessage());
        }
    }
}
