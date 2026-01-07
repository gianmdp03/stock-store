package com.stockstore.stockstore.online.dto.cartItem;

import com.stockstore.stockstore.shared.dto.product.ProductListDTO;

public record CartItemListDTO (ProductListDTO product, int amount){}
