package com.stockstore.stockstore.shared.dto.product;

import com.stockstore.stockstore.shared.dto.category.CategoryListDTO;

import java.math.BigDecimal;
import java.util.List;

public record ProductListDTO(Long id, String name, String imageUrl, BigDecimal price, List<CategoryListDTO> categories){
}
