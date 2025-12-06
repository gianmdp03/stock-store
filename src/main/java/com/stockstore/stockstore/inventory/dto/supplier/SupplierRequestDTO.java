package com.stockstore.stockstore.inventory.dto.supplier;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record SupplierRequestDTO(@NotNull String name, @NotNull String email, @NotNull Long phoneNumber, @NotNull @NotEmpty
                                 List<Long> productsId) {
}
