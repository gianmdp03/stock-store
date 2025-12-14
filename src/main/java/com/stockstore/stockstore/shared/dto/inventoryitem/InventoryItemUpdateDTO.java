package com.stockstore.stockstore.shared.dto.inventoryitem;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record InventoryItemUpdateDTO (@Positive int stock, @Future LocalDate expireDate){
}
