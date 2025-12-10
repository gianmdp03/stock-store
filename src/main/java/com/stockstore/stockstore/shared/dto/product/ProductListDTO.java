package com.stockstore.stockstore.shared.dto.product;

import java.math.BigDecimal;

public record ProductListDTO(Long id, String name, String imageUrl, BigDecimal price) {
}
