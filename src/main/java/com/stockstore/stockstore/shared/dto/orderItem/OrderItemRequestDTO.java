package com.stockstore.stockstore.shared.dto.orderItem;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderItemRequestDTO(@NotNull Long productId, @NotNull @Positive int amount) {
}
