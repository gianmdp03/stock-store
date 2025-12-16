package com.stockstore.stockstore.shared.dto.product;

import com.stockstore.stockstore.shared.dto.category.CategoryListDTO;
import com.stockstore.stockstore.shared.dto.inventoryitem.InventoryItemDetailDTO;
import com.stockstore.stockstore.shared.dto.orderItem.OrderItemDetailDTO;

import java.math.BigDecimal;
import java.util.List;

public record ProductDetailDTO(Long id,
                               String name,
                               String imageUrl,
                               BigDecimal price,
                               List<InventoryItemDetailDTO> inventoryItems,
                               List<CategoryListDTO> categories) {}
