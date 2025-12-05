package com.stockstore.stockstore.inventory.model;

import com.stockstore.stockstore.shared.model.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "suppliers")
@Getter
@Setter
@NoArgsConstructor
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Long phoneNumber;

    @OneToMany(mappedBy = "supplier")
    private List<Product> products = new ArrayList<>();

    @Builder
    public Supplier(String name, String email, Long phoneNumber, List<Product> products) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.products = products;
    }
}
