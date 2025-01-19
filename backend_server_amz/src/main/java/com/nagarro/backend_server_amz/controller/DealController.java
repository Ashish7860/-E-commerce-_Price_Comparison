package com.nagarro.backend_server_amz.controller;

import com.nagarro.backend_server_amz.dto.CategoryDealsResponse;
import com.nagarro.backend_server_amz.service.DealService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/backendserver1/amazon/deals")
public class DealController {

    private final DealService dealService;

    @Autowired
    public DealController(DealService dealService) {
        this.dealService = dealService;
    }

    @GetMapping("/{categoryName}")
    public ResponseEntity<CategoryDealsResponse> getDealsByCategory(@PathVariable String categoryName) {
        Optional<CategoryDealsResponse> dealsResponse = dealService.getDealsByCategory(categoryName);

        return dealsResponse.map(ResponseEntity::ok)
                            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
