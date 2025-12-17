package com.stockstore.stockstore.shared.dto.Batch;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record BatchRequestDTO(@NotEmpty List<BatchItemDTO> items) {
}
