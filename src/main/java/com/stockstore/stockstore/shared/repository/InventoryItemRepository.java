package com.stockstore.stockstore.shared.repository;

import com.stockstore.stockstore.shared.model.InventoryItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
    Page<InventoryItem> findAllByProductId(Long productId, Pageable pageable);
    Page<InventoryItem> findByExpireDate(LocalDate date, Pageable page);
}
