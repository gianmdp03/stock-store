package com.stockstore.stockstore.local.model;

import com.stockstore.stockstore.shared.model.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "local_order_items")
@NoArgsConstructor
public class LocalOrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private int quantity;

    @Column(precision = 19, scale = 2)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "local_order_id", nullable = false)
    private LocalOrder localOrder;

    public LocalOrderItem(Product product, int quantity, LocalOrder localOrder, BigDecimal price) {
        this.product = product;
        this.quantity = quantity;
        this.localOrder = localOrder;
        this.price = price;
    }
}
