package com.stockstore.stockstore.online.controller;

import com.stockstore.stockstore.online.dto.cart.CartListDTO;
import com.stockstore.stockstore.online.dto.cartItem.CartItemListDTO;
import com.stockstore.stockstore.online.dto.cartItem.CartItemRequestDTO;
import com.stockstore.stockstore.online.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping("/first-use")
    public ResponseEntity<CartListDTO> addCartToUser(Authentication authentication){
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.addCartToUser(authentication.getName()));
    }
    @PostMapping
    public ResponseEntity<CartItemListDTO> addItemToCart(Authentication authentication, @Valid @RequestBody CartItemRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.addItemToCart(authentication.getName(), dto));
    }
    @GetMapping
    public ResponseEntity<CartListDTO> viewCart(Authentication authentication){
        return ResponseEntity.status(HttpStatus.OK).body(cartService.viewCart(authentication.getName()));
    }
    @PatchMapping("/items/{cartItemId}/{amount}")
    public ResponseEntity<CartListDTO> modifyCartItemAmount(
            Authentication authentication,
            @PathVariable Long cartItemId,
            @PathVariable int amount){
        return ResponseEntity.status(HttpStatus.OK).body(cartService.modifyCartItemAmount(
                authentication.getName(), cartItemId, amount));
    }
    @DeleteMapping("/items/{cartItemId}")
    public ResponseEntity<Void> deleteCartItem(Authentication authentication, @PathVariable Long cartItemId){
        cartService.deleteCartItem(authentication.getName(), cartItemId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @DeleteMapping
    public ResponseEntity<Void> emptyCart(Authentication authentication){
        cartService.emptyCart(authentication.getName());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
