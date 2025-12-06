package com.stockstore.stockstore.shared.dto.category;

import com.stockstore.stockstore.shared.dto.product.ProductListDTO;

import java.util.List;

public record CategoryDetailDTO(Long id, String name, List<ProductListDTO> products) {}
