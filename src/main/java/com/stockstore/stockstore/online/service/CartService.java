package com.stockstore.stockstore.online.service;

import com.stockstore.stockstore.online.dto.cart.CartListDTO;
import com.stockstore.stockstore.online.dto.cartItem.CartItemListDTO;
import com.stockstore.stockstore.online.dto.cartItem.CartItemRequestDTO;

public interface CartService {
    CartListDTO addCartToUser(String email);
    CartItemListDTO addItemToCart(String email, CartItemRequestDTO dto);
    CartListDTO listCart(String email);
    CartListDTO modifyCartItemAmount(String email, Long cartItemId, int amount);
    void deleteCartItem(String email, Long cartItemId);
    void emptyCart(String email);
}
