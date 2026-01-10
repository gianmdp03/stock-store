package com.stockstore.stockstore.online.service.impl;

import com.stockstore.stockstore.exception.BadRequestException;
import com.stockstore.stockstore.exception.NotFoundException;
import com.stockstore.stockstore.online.dto.cart.CartListDTO;
import com.stockstore.stockstore.online.dto.cartItem.CartItemListDTO;
import com.stockstore.stockstore.online.dto.cartItem.CartItemRequestDTO;
import com.stockstore.stockstore.online.mapper.CartItemMapper;
import com.stockstore.stockstore.online.mapper.CartMapper;
import com.stockstore.stockstore.online.model.Cart;
import com.stockstore.stockstore.online.model.CartItem;
import com.stockstore.stockstore.online.repository.CartItemRepository;
import com.stockstore.stockstore.online.repository.CartRepository;
import com.stockstore.stockstore.online.service.CartService;
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
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final CartMapper cartMapper;
    private final CartItemMapper cartItemMapper;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public CartListDTO addCartToUser(String email){
        User user = userRepository.findByEmail(email).orElseThrow(()-> new NotFoundException("User does not exist"));
        Optional<Cart> existingCart = cartRepository.findByUser(user);
        if(existingCart.isPresent()){
            return cartMapper.toListDto(existingCart.get());
        }
        Cart cart = new Cart(user);
        cart = cartRepository.save(cart);

        return cartMapper.toListDto(cart);
    }

    @Override
    @Transactional
    public CartItemListDTO addItemToCart(String email, CartItemRequestDTO dto){
        Cart cart = cartRepository.findByUserEmail(email)
                .orElseThrow(()->new NotFoundException("Cart does not exist"));
        Product product = productRepository.findByIdAndEnabledTrue(dto.productId())
                .orElseThrow(()-> new NotFoundException("Product ID does not exist"));
        CartItem cartItem = cartItemMapper.toEntity(dto);
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem = cartItemRepository.save(cartItem);
        return cartItemMapper.toListDto(cartItem);
    }

    @Override
    public CartListDTO viewCart(String email){
        Cart cart = cartRepository.findByUserEmail(email)
                .orElseThrow(()->new NotFoundException("Cart does not exist"));
        return cartMapper.toListDto(cart);
    }

    @Override
    @Transactional
    public CartListDTO modifyCartItemAmount(String email, Long cartItemId, int quantity){
        if(quantity < 1){
            throw new BadRequestException("Amount can't be zero or negative");
        }
        Cart cart = cartRepository.findByUserEmail(email)
                .orElseThrow(()->new NotFoundException("Cart does not exist"));
        CartItem cartItem = cartItemRepository.findByIdAndCartId(cartItemId, cart.getId())
                .orElseThrow(()->new NotFoundException("CartItem does not exist"));
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
        cart = cartRepository.findByUserEmail(email)
                .orElseThrow(()->new NotFoundException("Cart does not exist"));
        return cartMapper.toListDto(cart);
    }

    @Override
    @Transactional
    public void deleteCartItem(String email, Long cartItemId){
        Cart cart = cartRepository.findByUserEmail(email)
                .orElseThrow(()->new NotFoundException("Cart does not exist"));
        cartItemRepository.deleteByIdAndCartId(cartItemId, cart.getId());
    }

    @Override
    @Transactional
    public void emptyCart(String email){
        Cart cart = cartRepository.findByUserEmail(email)
                .orElseThrow(()->new NotFoundException("Cart does not exist"));
        cartItemRepository.deleteByCart(cart);
    }
}
