package com.stockstore.stockstore.online.service;

import com.stockstore.stockstore.online.dto.wishlist.WishlistDetailDTO;

public interface WishlistService {
    WishlistDetailDTO addWishlistToUser(String email);
    WishlistDetailDTO addItemToWishlist(String email, Long productId);
    WishlistDetailDTO getWishlistByUser(String email);
    void deleteItemFromWishlist(String email, Long productId);
}
