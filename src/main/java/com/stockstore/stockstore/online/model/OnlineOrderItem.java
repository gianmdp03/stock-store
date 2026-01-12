package com.stockstore.stockstore.online.model;

import com.stockstore.stockstore.shared.model.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "online_order_items")
@Getter
@Setter
@NoArgsConstructor
public class OnlineOrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(precision = 19, scale = 2)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "online_order_id")
    private OnlineOrder onlineOrder;

    public OnlineOrderItem(int quantity, Product product, OnlineOrder onlineOrder, BigDecimal price) {
        this.quantity = quantity;
        this.product = product;
        this.onlineOrder = onlineOrder;
        this.price = price;
    }
    
}
