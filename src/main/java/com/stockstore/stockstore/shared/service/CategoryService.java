package com.stockstore.stockstore.shared.service;


import com.stockstore.stockstore.shared.dto.category.CategoryDetailDTO;
import com.stockstore.stockstore.shared.dto.category.CategoryListDTO;
import com.stockstore.stockstore.shared.dto.category.CategoryRequestDTO;
import com.stockstore.stockstore.shared.dto.category.CategoryUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    CategoryDetailDTO addCategory(CategoryRequestDTO dto);
    CategoryDetailDTO updateCategory(Long id, CategoryUpdateDTO dto);
    Page<CategoryListDTO> listCategories(Pageable pageable);
    void deleteCategory(Long categoryId);
}
