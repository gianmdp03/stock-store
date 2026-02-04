package com.stockstore.stockstore.inventory.dto.supplier;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record SupplierOrderDTO(@NotBlank Long productId, @NotNull @Positive int quantity){
}
