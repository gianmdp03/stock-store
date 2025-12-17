package com.stockstore.stockstore.inventory.repository;

import com.stockstore.stockstore.inventory.dto.supplier.SupplierListDTO;
import com.stockstore.stockstore.inventory.model.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Page<Supplier> findAllByNameContainingIgnoreCaseAndEnabledTrue(String name, Pageable pageable);
    Page<Supplier> findByEmailContainingIgnoreCaseAndEnabledTrue(String email, Pageable page);
    Optional<Supplier> findByName(String name);
    Page<Supplier> findByEnabledTrue(Pageable pageable);
    Optional<Supplier> findByIdAndEnabledTrue(Long id);
}
