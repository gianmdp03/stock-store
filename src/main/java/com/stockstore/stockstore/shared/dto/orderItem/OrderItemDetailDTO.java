package com.stockstore.stockstore.shared.dto.orderItem;

import com.stockstore.stockstore.shared.dto.product.ProductListDTO;

public record OrderItemDetailDTO (Long id, ProductListDTO productListDTO, int amount){
}
