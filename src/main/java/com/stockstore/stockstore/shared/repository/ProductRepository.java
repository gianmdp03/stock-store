package com.stockstore.stockstore.shared.repository;

import com.stockstore.stockstore.shared.dto.product.ProductListDTO;
import com.stockstore.stockstore.shared.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByIdInAndEnabledTrue(Iterable<Long> ids);
    Optional<Product> findByName(String name);
    Optional<Product> findByIdAndEnabledTrue(Long id);
    Optional<Product> findByBarcode(String name);
    Optional<Product> findByBarcodeAndEnabledTrue(String barcode);
    boolean existsByBarcode(String barcode);
    Page<Product> findByEnabledTrue(Pageable pageable);
    Page<Product> findByNameContainingIgnoreCaseAndEnabledTrue(String name, Pageable pageable);
    @Query("SELECT DISTINCT p FROM Product p JOIN p.inventoryItems i WHERE p.enabled = true AND i.stock > 0")
    Page<Product> findProductsWithStock(Pageable pageable);
    @Query("SELECT DISTINCT p FROM Product p JOIN p.inventoryItems i JOIN p.categories c WHERE i.stock > 0 AND c.id = :categoryId")
    Page<Product> findAvailableByCategory(@Param("categoryId") Long categoryId, Pageable pageable);}
