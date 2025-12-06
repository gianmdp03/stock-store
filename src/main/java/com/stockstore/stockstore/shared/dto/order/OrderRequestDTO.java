package com.stockstore.stockstore.shared.dto.order;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record OrderRequestDTO(@NotNull BigDecimal amount, String description, @PastOrPresent LocalDate saleDate, @NotNull @NotEmpty List<Long> productsId) {}
