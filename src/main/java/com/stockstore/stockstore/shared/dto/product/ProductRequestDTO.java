package com.stockstore.stockstore.shared.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record ProductRequestDTO(@NotBlank String name,
                                @NotBlank String imageUrl,
                                @NotNull @Positive BigDecimal price,
                                @NotBlank String barcode,
                                @NotEmpty List<Long> categoriesId) {}
