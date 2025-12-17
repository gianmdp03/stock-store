package com.stockstore.stockstore.shared.dto.Batch;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record BatchItemDTO(@NotNull Long productId, @NotNull @Positive int quantity) {
}
