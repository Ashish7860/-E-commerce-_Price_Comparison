package com.nagarro.ecommers.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import com.nagarro.ecommers.model.DealItem;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CombinedDealService {

    private final WebClient webClient;

    public CombinedDealService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    private List<DealItem> fetchFromService(String baseUrl, String categoryName, String serviceName) {
        try {
            CategoryDealsResponse response = webClient.get()
                    .uri(baseUrl + "/deals/{categoryName}", categoryName)
                    .retrieve()
                    .bodyToMono(CategoryDealsResponse.class)
                    .block();  // Blocking call for synchronous execution

            List<DealItem> deals = response != null ? response.getDealItems() : new ArrayList<>();
            System.out.println(serviceName + " returned " + deals.size() + " items.");
            return deals;

        } catch (WebClientResponseException ex) {
            System.out.println("Error from " + serviceName + ": " + ex.getMessage());
            return new ArrayList<>();
        } catch (Exception ex) {
            System.out.println("Unexpected error from " + serviceName + ": " + ex.getMessage());
            return new ArrayList<>();
        }
    }

    public List<DealItem> getBestDealsByCategory(String categoryName) {
        // Fetch data from each backend service synchronously
        List<DealItem> amazonDeals = fetchFromService("http://localhost:8081/backendserver1/amazon", categoryName, "Amazon");
        List<DealItem> ebayDeals = fetchFromService("http://localhost:8082/backendserver2/ebay", categoryName, "eBay");
        List<DealItem> walmartDeals = fetchFromService("http://localhost:8083/backendserver3/walmart", categoryName, "Walmart");

        // Combine all deals into one list
        List<DealItem> combinedDeals = new ArrayList<>();
        combinedDeals.addAll(amazonDeals);
        combinedDeals.addAll(ebayDeals);
        combinedDeals.addAll(walmartDeals);

        // Apply filtering and sorting logic
        return combinedDeals.stream()
                .filter(this::isValidDeal) 
                .sorted(this::compareDeals)  // Sort by discount, then price
                .collect(Collectors.toList());
    }

    private boolean isValidDeal(DealItem dealItem) {
        // Check stock availability
        if (dealItem.getStock() <= 0) {
            return false;
        }

        // Check if the current date is within the deal start and end dates
        LocalDate currentDate = LocalDate.now();
        return currentDate.isAfter(dealItem.getDealStartDate()) && currentDate.isBefore(dealItem.getDealEndDate());
    }

 // Sorting logic
    private int compareDeals(DealItem item1, DealItem item2) {
        // Compare by discount amount, higher discounts come first
        int discountComparison = Double.compare(item2.getDiscountAmount(), item1.getDiscountAmount());
        if (discountComparison != 0) {
            return discountComparison;
        }

        // If discounts are equal, compare by price, lower price comes first
        return Double.compare(item1.getMarketingPrice().getPrice(), item2.getMarketingPrice().getPrice());
    }


    // Inner class to match the response structure
    private static class CategoryDealsResponse {
        private List<DealItem> dealItems;

        public List<DealItem> getDealItems() {
            return dealItems != null ? dealItems : new ArrayList<>();
        }
    }
}
