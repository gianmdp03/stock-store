package com.stockstore.stockstore.inventory.dto.supplier;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SupplierRequestDTO(@NotBlank String name,
                                 @NotBlank @Email String email,
                                 @NotBlank String phoneNumber) {}
