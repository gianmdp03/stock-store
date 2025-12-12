package com.stockstore.stockstore.shared.dto.order;

import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record OrderRequestDTO(@PastOrPresent LocalDate saleDate) {}
