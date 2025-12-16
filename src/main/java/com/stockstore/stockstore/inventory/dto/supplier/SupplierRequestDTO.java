package com.stockstore.stockstore.inventory.dto.supplier;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record SupplierRequestDTO(@NotBlank String name,
                                 @NotBlank @Email String email,
                                 @NotBlank String phoneNumber,
                                 @NotEmpty List<Long> productIds) {}
