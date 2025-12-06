package com.stockstore.stockstore.shared.dto.inventoryitem;

import com.stockstore.stockstore.shared.dto.product.ProductListDTO;

import java.time.LocalDate;

public record InventoryItemDetailDTO(Long id, ProductListDTO product, int stock, LocalDate expireDate) {}
