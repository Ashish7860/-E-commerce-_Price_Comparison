package com.nagarro.backend_server_walmart.controller;

import com.nagarro.backend_server_walmart.dto.CategoryDealsResponse;
import com.nagarro.backend_server_walmart.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DealController {

    @Autowired
    private DealService dealService;

    @GetMapping("/backendserver3/walmart/deals/{categoryName}")
    public ResponseEntity<CategoryDealsResponse> getDeals(@PathVariable String categoryName) {
        CategoryDealsResponse response = dealService.getDealsByCategory(categoryName);
        return ResponseEntity.ok(response);
    }
}