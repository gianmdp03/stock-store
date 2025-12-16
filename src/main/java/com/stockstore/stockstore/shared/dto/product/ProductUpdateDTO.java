package com.stockstore.stockstore.shared.dto.product;

import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record ProductUpdateDTO (String name, String imageUrl, @Positive BigDecimal price, List<Long> categoriesId){
}
