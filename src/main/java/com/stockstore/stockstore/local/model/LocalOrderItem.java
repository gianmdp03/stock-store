package com.stockstore.stockstore.local.model;

import com.stockstore.stockstore.shared.model.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "local_order_id")
    private LocalOrder localOrder;

    public LocalOrderItem(Product product, int quantity, LocalOrder localOrder) {
        this.product = product;
        this.quantity = quantity;
        this.localOrder = localOrder;
    }
}
