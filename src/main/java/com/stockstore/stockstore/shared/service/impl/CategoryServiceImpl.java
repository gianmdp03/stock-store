package com.stockstore.stockstore.shared.service.impl;

import com.stockstore.stockstore.exception.NotFoundException;
import com.stockstore.stockstore.shared.dto.category.CategoryDetailDTO;
import com.stockstore.stockstore.shared.dto.category.CategoryListDTO;
import com.stockstore.stockstore.shared.dto.category.CategoryRequestDTO;
import com.stockstore.stockstore.shared.mapper.CategoryMapper;
import com.stockstore.stockstore.shared.model.Category;
import com.stockstore.stockstore.shared.repository.CategoryRepository;
import com.stockstore.stockstore.shared.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.NotFound;
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
    Category category = categoryRepository.save(categoryMapper.toEntity(dto));
    return categoryMapper.toDetailDto(category);
    }

    @Override
    public Page<CategoryListDTO> listCategories(Pageable pageable) {
        Page<Category> categoryPage = categoryRepository.findAll(pageable);
        if(categoryPage.isEmpty()){
            throw new NotFoundException("Category not found");
        }
        return categoryPage.map(categoryMapper::toListDto);
    }

    @Override
    @Transactional
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new NotFoundException("Category not found"));
        categoryRepository.delete(category);
    }

}
