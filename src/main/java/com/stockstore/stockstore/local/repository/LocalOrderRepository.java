package com.stockstore.stockstore.local.repository;

import com.stockstore.stockstore.local.model.LocalOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalOrderRepository extends JpaRepository<LocalOrder, Long> {
}
