package com.nagarro.backend_server_walmart.repository;

import com.nagarro.backend_server_walmart.model.DealItem;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealItemRepository extends JpaRepository<DealItem, String> {


    List<DealItem> findByBrand(String brand);

    List<DealItem> findBySize(String size);

    List<DealItem> findByProductTitleContaining(String title);
}

