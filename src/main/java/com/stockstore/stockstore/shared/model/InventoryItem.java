package com.stockstore.stockstore.shared.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "inventoryitems")
@Getter
@Setter
@NoArgsConstructor
public class InventoryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private int stock;

    @Column(nullable = false)
    private LocalDate expireDate;

    public InventoryItem(Product product, int stock, LocalDate expireDate) {
        this.product = product;
        this.stock = stock;
        this.expireDate = expireDate;
    }
}
