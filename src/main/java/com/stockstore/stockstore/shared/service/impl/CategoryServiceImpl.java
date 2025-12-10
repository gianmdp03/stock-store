package com.stockstore.stockstore.shared.service.impl;

import com.stockstore.stockstore.shared.dto.category.CategoryDetailDTO;
import com.stockstore.stockstore.shared.dto.category.CategoryListDTO;
import com.stockstore.stockstore.shared.dto.category.CategoryRequestDTO;
import com.stockstore.stockstore.shared.mapper.CategoryMapper;
import com.stockstore.stockstore.shared.repository.CategoryRepository;
import com.stockstore.stockstore.shared.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    @Transactional
    public CategoryDetailDTO addCategory(CategoryRequestDTO dto) {
        return null;
    }

    @Override
    public Page<CategoryListDTO> listCategories(Pageable pageable) {
        return null;
    }

    @Override
    @Transactional
    public void deleteCategory(Long categoryId) {

    }
}
