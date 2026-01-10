package com.stockstore.stockstore.local.model;

import com.stockstore.stockstore.local.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
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

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private BigDecimal totalAmount;

    public LocalOrder(LocalDateTime saleDate, PaymentMethod paymentMethod) {
        this.saleDate = saleDate;
        this.paymentMethod = paymentMethod;
    }
}
