package com.stockstore.stockstore.shared.repository;

import com.stockstore.stockstore.shared.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findAllBySaleDate(LocalDate saleDate, Pageable pageable);
}
