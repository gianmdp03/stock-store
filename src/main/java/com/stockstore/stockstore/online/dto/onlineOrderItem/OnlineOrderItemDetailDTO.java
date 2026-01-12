package com.stockstore.stockstore.online.dto.onlineOrderItem;

import com.stockstore.stockstore.shared.dto.product.ProductListDTO;

import java.math.BigDecimal;

public record OnlineOrderItemDetailDTO(Long id, ProductListDTO product, int amount, BigDecimal price){
}
