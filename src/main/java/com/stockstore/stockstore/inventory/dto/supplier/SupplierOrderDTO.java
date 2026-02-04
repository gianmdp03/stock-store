package com.stockstore.stockstore.inventory.dto.supplier;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record SupplierOrderDTO(@NotNull(message = "El ID del producto es obligatorio")
                               @Positive(message = "El ID debe ser mayor a 0")
                               Long productId,

                               @NotNull
                               @Positive
                               int quantity){
}
