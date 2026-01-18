package com.stockstore.stockstore.online.dto.address;

import jakarta.validation.constraints.NotBlank;

public record AddressRequestDTO(@NotBlank String name,
                                @NotBlank String street,
                                @NotBlank String number,
                                String apartment,
                                @NotBlank String zipCode,
                                @NotBlank String city,
                                @NotBlank String province) {}
