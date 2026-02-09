package com.stockstore.stockstore.shared.dto.product;

import com.stockstore.stockstore.inventory.dto.supplier.SupplierListDTO;
import com.stockstore.stockstore.shared.dto.category.CategoryListDTO;
import com.stockstore.stockstore.shared.dto.inventoryitem.InventoryItemDetailDTO;

import java.math.BigDecimal;
import java.util.List;

public record ProductDetailDTO(Long id,
                               String name,
                               String imageUrl,
                               BigDecimal price,
                               String barcode,
                               List<InventoryItemDetailDTO> inventoryItems,
                               List<CategoryListDTO> categories,
                               List<SupplierListDTO> suppliers) {}

