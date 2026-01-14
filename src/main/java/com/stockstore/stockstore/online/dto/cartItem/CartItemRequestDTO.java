package com.stockstore.stockstore.online.dto.cartItem;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CartItemRequestDTO(@NotNull Long productId, @NotNull @Positive int quantity) {
}
