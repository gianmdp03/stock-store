package com.stockstore.stockstore.local.dto.localOrderItem;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record LocalOrderItemRequestDTO (@NotNull Long productId,
                                        @NotNull @Positive int quantity){}
