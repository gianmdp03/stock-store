package com.stockstore.stockstore.online.dto.orderItem;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OnlineOrderItemRequestDTO(@NotNull Long productId, @NotNull @Positive int amount, @NotBlank String price) {
}
