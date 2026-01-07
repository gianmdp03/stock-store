package com.stockstore.stockstore.online.controller;

import com.stockstore.stockstore.online.dto.cart.CartListDTO;
import com.stockstore.stockstore.online.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping("/first-use")
    public ResponseEntity<CartListDTO> addCartToUser(Authentication authentication){
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.addCartToUser(authentication.getName()));
    }
}
