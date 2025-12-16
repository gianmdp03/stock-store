package com.stockstore.stockstore.shared.repository;

import com.stockstore.stockstore.shared.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
    Optional<Product> findByIdAndEnabledTrue(Long id);
    Page<Product> findByEnabledTrue(Pageable pageable);
    Page<Product> findByNameContainingIgnoreCaseAndEnabledTrue(String name, Pageable pageable);
}
