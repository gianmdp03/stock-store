package com.stockstore.stockstore.inventory.repository;

import com.stockstore.stockstore.inventory.model.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Page<Supplier> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Supplier> findByEmailContainingIgnoreCase(String email, Pageable page);
}
