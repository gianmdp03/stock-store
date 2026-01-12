package com.stockstore.stockstore.online.service.impl;

import com.stockstore.stockstore.exception.NotFoundException;
import com.stockstore.stockstore.online.dto.wishlist.WishlistDetailDTO;
import com.stockstore.stockstore.online.mapper.WishlistMapper;
import com.stockstore.stockstore.online.model.Wishlist;
import com.stockstore.stockstore.online.repository.WishlistRepository;
import com.stockstore.stockstore.online.service.WishlistService;
import com.stockstore.stockstore.security.user.model.User;
import com.stockstore.stockstore.security.user.repository.UserRepository;
import com.stockstore.stockstore.shared.model.Product;
import com.stockstore.stockstore.shared.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {
    private final WishlistRepository wishlistRepository;
    private final WishlistMapper wishlistMapper;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public WishlistDetailDTO addWishlistToUser(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(()-> new NotFoundException("User ID does not exist"));
        Optional<Wishlist> existingWishlist = wishlistRepository.findByUser(user);
        if(existingWishlist.isPresent()){
            return wishlistMapper.toDetailDto(existingWishlist.get());
        }
        Wishlist wishlist = new Wishlist(user);
        wishlist = wishlistRepository.save(wishlist);

        return wishlistMapper.toDetailDto(wishlist);
    }

    @Override
    @Transactional
    public WishlistDetailDTO addItemToWishlist(String email, Long productId) {
        Wishlist wishlist = wishlistRepository.findByUserEmail(email).orElseThrow(()-> new NotFoundException("Invalid Wishlist"));
        Product product = productRepository.findByIdAndEnabledTrue(productId).orElseThrow(()-> new NotFoundException("Product ID does not exist"));
        wishlist.getProducts().add(product);
        return wishlistMapper.toDetailDto(wishlist);
    }

    @Override
    public WishlistDetailDTO getWishlistByUser(String email){
        Wishlist wishlist = wishlistRepository.findByUserEmail(email).orElseThrow(()-> new NotFoundException("Invalid Wishlist"));
        return wishlistMapper.toDetailDto(wishlist);
    }

    @Override
    @Transactional
    public void deleteItemFromWishlist(String email, Long productId) {
        User user = userRepository.findByEmail(email).orElseThrow(()-> new NotFoundException("Invalid Wishlist"));
        wishlistRepository.deleteByProductIdAndUser(productId, user);
    }
}
