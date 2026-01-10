package com.stockstore.stockstore.local.dto.localOrderItem;

import com.stockstore.stockstore.shared.dto.product.ProductListDTO;

public record LocalOrderItemDetailDTO(ProductListDTO product, int quantity) {}
