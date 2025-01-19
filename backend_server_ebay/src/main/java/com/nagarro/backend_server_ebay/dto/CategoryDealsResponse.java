package com.nagarro.backend_server_ebay.dto;

import com.nagarro.backend_server_ebay.model.DealItem;
import java.util.List;

public class CategoryDealsResponse {
    private String categoryName;
    private List<DealItem> dealItems;
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public List<DealItem> getDealItems() {
		return dealItems;
	}
	public void setDealItems(List<DealItem> dealItems) {
		this.dealItems = dealItems;
	}

   
}
