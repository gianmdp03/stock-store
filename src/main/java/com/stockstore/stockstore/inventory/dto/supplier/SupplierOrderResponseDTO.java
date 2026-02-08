package com.stockstore.stockstore.inventory.dto.supplier;

import java.time.LocalDateTime;

public record SupplierOrderResponseDTO(
        Long id,
        String supplierName,
        LocalDateTime date,
        Integer totalItems,
        String status,
         Double totalCost
) {}