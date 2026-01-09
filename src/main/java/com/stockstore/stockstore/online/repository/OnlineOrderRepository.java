package com.stockstore.stockstore.online.repository;

import com.stockstore.stockstore.online.model.OnlineOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface OnlineOrderRepository extends JpaRepository<OnlineOrder, Long> {
    Page<OnlineOrder> findAllBySaleDate(LocalDateTime saleDate, Pageable pageable);
    Page<OnlineOrder> findBySaleDateBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);
}
