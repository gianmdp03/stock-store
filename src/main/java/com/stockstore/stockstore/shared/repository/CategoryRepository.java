package com.stockstore.stockstore.shared.repository;

import com.stockstore.stockstore.shared.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Page<Category> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
}
