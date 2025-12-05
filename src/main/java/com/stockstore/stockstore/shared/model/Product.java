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
@Getter
@Setter
@NoArgsConstructor
@Table(name = "productos")
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

    @OneToMany(mappedBy = "product")
    private List<Order> orders =  new ArrayList<>();

    @ManyToMany(mappedBy = "products")
    private List<Category> categories =new ArrayList<>();


   @Builder
    public Product(String name, String imageurl, BigDecimal price) {
        this.name = name;
        this.imageurl = imageurl;
        this.price = price;
    }
    
}
