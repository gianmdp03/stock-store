package com.stockstore.stockstore.inventory.dto.supplier;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record SupplierOrderDTO(@NotBlank String productName, @NotNull @Positive int quantity){
}
