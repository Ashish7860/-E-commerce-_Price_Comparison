package com.nagarro.backend_server_ebay.repository;

import com.nagarro.backend_server_ebay.model.DealItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DealItemRepository extends JpaRepository<DealItem, String> {

    List<DealItem> findByBrand(String brand);

    List<DealItem> findBySize(String size);

    List<DealItem> findByProductTitleContaining(String title);
}
