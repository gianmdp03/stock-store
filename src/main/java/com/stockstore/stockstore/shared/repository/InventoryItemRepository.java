package com.stockstore.stockstore.shared.repository;

import com.stockstore.stockstore.shared.model.InventoryItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
    Page<InventoryItem> findAllByProductId(Long productId, Pageable pageable);
    List<InventoryItem> findByProductIdAndStockGreaterThanOrderByExpireDateAsc(Long productId, int stock);

    List<InventoryItem> findTop10ByOrderByStockDesc();

    List<InventoryItem> findByStockLessThan(int stockLimit);
    Page<InventoryItem> findByProduct_NameContainingIgnoreCase(String name, Pageable pageable);
}
