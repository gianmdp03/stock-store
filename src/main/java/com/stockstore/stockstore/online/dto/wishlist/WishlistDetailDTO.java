package com.stockstore.stockstore.online.dto.wishlist;

import com.stockstore.stockstore.shared.dto.product.ProductListDTO;

import java.util.List;

public record WishlistDetailDTO(Long id, List<ProductListDTO> products) {}
