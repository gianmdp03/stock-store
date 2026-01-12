package com.stockstore.stockstore.local.dto.localOrderItem;

import com.stockstore.stockstore.shared.dto.product.ProductListDTO;

import java.math.BigDecimal;

public record LocalOrderItemDetailDTO(Long id, ProductListDTO product, int quantity, BigDecimal price) {}
