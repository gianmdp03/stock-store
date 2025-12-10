package com.stockstore.stockstore.shared.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record ProductRequestDTO(@NotBlank String name,
                                String imageUrl,
                                @NotNull @Positive BigDecimal price,
                                @NotNull Long supplierId,
                                @NotNull @NotEmpty List<Long> categoriesId) {}
