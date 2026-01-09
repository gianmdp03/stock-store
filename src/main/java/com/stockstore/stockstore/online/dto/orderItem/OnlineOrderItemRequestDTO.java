package com.stockstore.stockstore.online.dto.orderItem;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record OnlineOrderItemRequestDTO(@NotNull Long productId, @NotNull @Positive int amount, @NotNull @Positive BigDecimal price) {
}
