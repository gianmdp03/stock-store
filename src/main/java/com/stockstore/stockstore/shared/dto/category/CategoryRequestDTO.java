package com.stockstore.stockstore.shared.dto.category;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequestDTO(@NotBlank String name) {
}
