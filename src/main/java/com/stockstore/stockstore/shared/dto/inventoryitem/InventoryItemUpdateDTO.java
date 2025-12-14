package com.stockstore.stockstore.shared.dto.inventoryitem;

import java.time.LocalDate;

public record InventoryItemUpdateDTO (int stock, LocalDate expireDate){
}
