package com.nagarro.ecommers.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.ecommers.exception.ItemNotFoundException;
import com.nagarro.ecommers.model.DealItem;
import com.nagarro.ecommers.service.CombinedDealService;

import java.util.List;

@RestController
@RequestMapping("/deals")
public class DealsController {
    private final CombinedDealService combinedDealService;

    public DealsController(CombinedDealService combinedDealService) {
        this.combinedDealService = combinedDealService;
    }

    @GetMapping("/{categoryName}")
    public List<DealItem> getDealsByCategory(@PathVariable String categoryName) {
        List<DealItem> deals = combinedDealService.getBestDealsByCategory(categoryName);
        if (deals.isEmpty()) {
            throw new ItemNotFoundException("No deals found for category: " + categoryName);
        }
        return deals;
    }
}
