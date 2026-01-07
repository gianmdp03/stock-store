package com.stockstore.stockstore.online.dto.cart;

import com.stockstore.stockstore.online.dto.cartItem.CartItemListDTO;

import java.util.List;

public record CartListDTO (List<CartItemListDTO> items) {}
