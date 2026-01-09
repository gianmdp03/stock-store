package com.stockstore.stockstore.online.repository;

import com.stockstore.stockstore.online.model.OnlineOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OnlineOrderItemRepository extends JpaRepository<OnlineOrderItem, Long> {
}
