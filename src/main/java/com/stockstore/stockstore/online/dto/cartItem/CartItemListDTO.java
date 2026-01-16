package com.stockstore.stockstore.online.dto.cartItem;

import com.stockstore.stockstore.shared.dto.product.ProductListDTO;

public record CartItemListDTO (Long id, ProductListDTO product, int quantity){}
