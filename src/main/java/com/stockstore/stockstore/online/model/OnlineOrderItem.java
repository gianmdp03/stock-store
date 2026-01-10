package com.stockstore.stockstore.online.model;

import com.stockstore.stockstore.shared.model.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.Setter;

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

    @ManyToOne
    @JoinColumn(name = "online_order_id")
    private OnlineOrder onlineOrder;

    public OnlineOrderItem(int quantity, Product product, OnlineOrder onlineOrder) {
        this.quantity = quantity;
        this.product = product;
        this.onlineOrder = onlineOrder;
    }
    
}
