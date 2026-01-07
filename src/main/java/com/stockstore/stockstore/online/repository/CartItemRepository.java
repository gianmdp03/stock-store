package com.stockstore.stockstore.online.repository;

import com.stockstore.stockstore.online.model.Cart;
import com.stockstore.stockstore.online.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    @Modifying
    @Query("DELETE FROM CartItem ci WHERE ci.cart = :cart")
    void deleteByCart(Cart cart);
    void deleteByIdAndCartId(Long id, Long cartId);
    Optional<CartItem> findByIdAndCartId(Long id, Long cartId);
}
