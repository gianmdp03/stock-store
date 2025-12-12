package com.stockstore.stockstore.shared.repository;

import com.stockstore.stockstore.shared.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
