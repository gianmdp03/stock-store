package com.stockstore.stockstore.shared.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.Setter;

@Entity
@Table(name = "orderitem")
@Getter
@Setter
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int amount;

    @ManyToOne
    @JoinColumn(name = "order_item_id")
    private Product product;

    public OrderItem(int amount, Product product) {
        this.amount = amount;
        this.product = product;
    }
    
}
