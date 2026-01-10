package com.stockstore.stockstore.local.repository;

import com.stockstore.stockstore.local.model.LocalOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalOrderItemRepository extends JpaRepository<LocalOrderItem, Long> {
}
