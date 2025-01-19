package com.nagarro.ecommers.controller;

import com.nagarro.ecommers.exception.ItemNotFoundException;
import com.nagarro.ecommers.model.DealItem;
import com.nagarro.ecommers.model.MarketingPrice;
import com.nagarro.ecommers.model.OriginalPrice;
import com.nagarro.ecommers.service.CombinedDealService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DealsControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CombinedDealService combinedDealService;

    @InjectMocks
    private DealsController dealsController;

    private List<DealItem> mockDealItems;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(dealsController).build();
        
        // Initialize mock data
        mockDealItems = Arrays.asList(
            createDealItem("ITEM1", "Product 1", "Large", "Brand A", 100.0, 80.0, 5),
            createDealItem("ITEM2", "Product 2", "Medium", "Brand B", 200.0, 160.0, 3)
        );
    }

    @Test
    void whenValidCategory_thenReturnsDeals() throws Exception {
        String category = "electronics";
        when(combinedDealService.getBestDealsByCategory(category)).thenReturn(mockDealItems);

        mockMvc.perform(get("/deals/{category}", category))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$", hasSize(2)))
               .andExpect(jsonPath("$[0].itemId").value("ITEM1"))
               .andExpect(jsonPath("$[0].productTitle").value("Product 1"))
               .andExpect(jsonPath("$[0].brand").value("Brand A"))
               .andExpect(jsonPath("$[0].price.price").value(100.0))
               .andExpect(jsonPath("$[0].marketingPrice.price").value(80.0));
    }

//    @Test
//    void whenCategoryHasNoDeals_thenThrowsItemNotFoundException() throws Exception {
//        String category = "nonexistent";
//        when(combinedDealService.getBestDealsByCategory(category)).thenReturn(new ArrayList<>());
//
//        mockMvc.perform(get("/deals/{category}", category))
//               .andExpect(status().isNotFound());
//    }

    @Test
    void testDirectControllerMethod() {
        String category = "electronics";
        when(combinedDealService.getBestDealsByCategory(category)).thenReturn(mockDealItems);

        List<DealItem> result = dealsController.getDealsByCategory(category);
        assertEquals(2, result.size());
        assertEquals("ITEM1", result.get(0).getItemId());
        assertEquals(20.0, result.get(0).getDiscountAmount(), 0.001);
    }

    @Test
    void testDirectControllerMethodThrowsException() {
        String category = "nonexistent";
        when(combinedDealService.getBestDealsByCategory(category)).thenReturn(new ArrayList<>());

        assertThrows(ItemNotFoundException.class, () -> {
            dealsController.getDealsByCategory(category);
        });
    }

    private DealItem createDealItem(String itemId, String productTitle, String size, 
                                   String brand, double originalPrice, double marketingPrice, 
                                   int stock) {
        DealItem item = new DealItem();
        item.setItemId(itemId);
        item.setProductTitle(productTitle);
        item.setSize(size);
        item.setBrand(brand);
        item.setImageUrl("http://example.com/image/" + itemId + ".jpg");
        
        OriginalPrice origPrice = new OriginalPrice();
        origPrice.setPrice(originalPrice);
        item.setPrice(origPrice);
        
        MarketingPrice markPrice = new MarketingPrice();
        markPrice.setPrice(marketingPrice);
        item.setMarketingPrice(markPrice);
        
        item.setStock(stock);
        item.setDealStartDate(LocalDate.now().minusDays(1));
        item.setDealEndDate(LocalDate.now().plusDays(1));
        
        return item;
    }
}