package com.stockstore.stockstore.online.dto.preference;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PreferenceRequestDTO (@NotNull Long productId, @NotNull @Positive int quantity){
}
