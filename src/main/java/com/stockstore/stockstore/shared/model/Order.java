package com.stockstore.stockstore.shared.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
public class Order {    //PRIMERO SE CREA ORDER, Y DESPUES TODOS LOS ORDER ITEM SE LINKEAN A ESA ORDER
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String description;

    @Column(nullable = false)
    private LocalDate saleDate;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    public Order(String description, LocalDate saleDate) {
        this.description = description;
        this.saleDate = saleDate;
    }
}
