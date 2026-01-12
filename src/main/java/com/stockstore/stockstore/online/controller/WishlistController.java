package com.stockstore.stockstore.online.controller;

import com.stockstore.stockstore.online.dto.wishlist.WishlistDetailDTO;
import com.stockstore.stockstore.online.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wishlists")
@RequiredArgsConstructor
public class WishlistController {
    private final WishlistService wishlistService;

    @PostMapping("/first-use")
    public ResponseEntity<WishlistDetailDTO> addWishlistToUser(Authentication authentication){
        return ResponseEntity.status(HttpStatus.OK).body(wishlistService.addWishlistToUser(authentication.getName()));
    }

    @PostMapping("/{productId}")
    public ResponseEntity<WishlistDetailDTO> addItemToWishlist(Authentication authentication, @PathVariable Long productId){
        return ResponseEntity.status(HttpStatus.CREATED).body(wishlistService.addItemToWishlist(authentication.getName(), productId));
    }

    @GetMapping
    public ResponseEntity<WishlistDetailDTO> getWishlistByUser(Authentication authentication){
        return ResponseEntity.status(HttpStatus.OK).body(wishlistService.getWishlistByUser(authentication.getName()));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteItemFromWishlist(Authentication authentication, @PathVariable Long productId){
        wishlistService.deleteItemFromWishlist(authentication.getName(), productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
