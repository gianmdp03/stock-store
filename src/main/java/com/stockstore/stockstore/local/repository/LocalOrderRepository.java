package com.stockstore.stockstore.local.repository;

import com.stockstore.stockstore.local.model.LocalOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface LocalOrderRepository extends JpaRepository<LocalOrder, Long> {
    Page<LocalOrder> findAllBySaleDate(LocalDateTime saleDate, Pageable pageable);
    Page<LocalOrder> findBySaleDateBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);
}
