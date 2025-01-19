package com.nagarro.backend_server_amz.controller;

import com.nagarro.backend_server_amz.dto.CategoryDealsResponse;
import com.nagarro.backend_server_amz.service.DealService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DealControllerTest {

    @Mock
    private DealService dealService;

    @InjectMocks
    private DealController dealController;

    private CategoryDealsResponse mockCategoryDealsResponse;

    @BeforeEach
    void setUp() {
        // Setup a mock response for the service layer
        mockCategoryDealsResponse = new CategoryDealsResponse();
        mockCategoryDealsResponse.setCategoryName("jeans");
    }

    @Test
    void testGetDealsByCategory_ValidCategory() {
        // Mock the behavior of the service layer for a valid category
        when(dealService.getDealsByCategory("jeans"))
                .thenReturn(Optional.of(mockCategoryDealsResponse));

        // Test the controller's endpoint method
        ResponseEntity<CategoryDealsResponse> response = dealController.getDealsByCategory("jeans");

        // Verify the results
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("jeans", response.getBody().getCategoryName());

        // Verify that the service layer was called once
        verify(dealService, times(1)).getDealsByCategory("jeans");
    }

    @Test
    void testGetDealsByCategory_InvalidCategory() {
        // Mock the service to return an empty Optional for an invalid category
        when(dealService.getDealsByCategory("invalidCategory"))
                .thenReturn(Optional.empty());

        // Test the controller's endpoint method with an invalid category
        ResponseEntity<CategoryDealsResponse> response = dealController.getDealsByCategory("invalidCategory");

        // Verify the results
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        // Verify that the service layer was called once
        verify(dealService, times(1)).getDealsByCategory("invalidCategory");
    }

    @Test
    void testGetDealsByCategory_NullCategory() {
        // Simulate null category handling in the service layer
        when(dealService.getDealsByCategory(null))
                .thenReturn(Optional.empty());

        // Test the controller with a null category
        ResponseEntity<CategoryDealsResponse> response = dealController.getDealsByCategory(null);

        // Verify the results
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        // Verify that the service layer was called once
        verify(dealService, times(1)).getDealsByCategory(null);
    }

    @Test
    void testGetDealsByCategory_EmptyCategory() {
        // Simulate empty category handling in the service layer
        when(dealService.getDealsByCategory(""))
                .thenReturn(Optional.empty());

        // Test the controller with an empty category
        ResponseEntity<CategoryDealsResponse> response = dealController.getDealsByCategory("");

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(dealService, times(1)).getDealsByCategory("");
    }

    @Test
    void testGetDealsByCategory_CategoryNotPresentInMap() {
        // Mock the service to return an empty Optional for a category not present in the map
        when(dealService.getDealsByCategory("notInMap"))
                .thenReturn(Optional.empty());

        // Test the controller with a category that doesn't exist in the map
        ResponseEntity<CategoryDealsResponse> response = dealController.getDealsByCategory("notInMap");

        
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        // Verify that the service layer was called once
        verify(dealService, times(1)).getDealsByCategory("notInMap");
    }
}
