package com.stockstore.stockstore.online.model;

import com.stockstore.stockstore.security.user.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
@Getter
@Setter
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> items = new ArrayList<>();

    public Cart(User user) {
        this.user = user;
    }

    /*public BigDecimal getTotalAmount() {
            return items.stream()
                    .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getAmount())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }*/
}
