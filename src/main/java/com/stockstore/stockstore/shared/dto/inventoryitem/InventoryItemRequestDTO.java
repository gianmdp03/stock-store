package com.stockstore.stockstore.shared.dto.inventoryitem;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record InventoryItemRequestDTO(@NotNull Long productId,
                                      @NotNull int stock,
                                      @NotNull @Future LocalDate expireDate) {}