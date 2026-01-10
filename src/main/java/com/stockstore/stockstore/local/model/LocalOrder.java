package com.stockstore.stockstore.local.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "local_orders")
@NoArgsConstructor
public class LocalOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime saleDate;

    @OneToMany(mappedBy = "localOrder")
    private List<LocalOrderItem> localOrderItems;

    //Metodo pago

    public LocalOrder(LocalDateTime saleDate, List<LocalOrderItem> localOrderItems) {
        this.saleDate = saleDate;
        this.localOrderItems = localOrderItems;
    }
}
