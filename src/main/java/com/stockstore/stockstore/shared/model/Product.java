package com.stockstore.stockstore.shared.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String imageurl;

    @Column(precision = 19, scale = 2, nullable = false)
    private BigDecimal price;

    @OneToMany(mappedBy = "product")
    private List<InventoryItem> inventoryItems =  new ArrayList<>();

    @OneToMany(mappedBy = "products")
    private List<Order> orders =  new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "products_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories =new ArrayList<>();

    @Builder
    public Product(String name, String imageurl, BigDecimal price) {
        this.name = name;
        this.imageurl = imageurl;
        this.price = price;
    }
}
