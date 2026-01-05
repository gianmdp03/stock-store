package com.stockstore.stockstore.shared.model;

import com.stockstore.stockstore.security.user.model.User;
import com.stockstore.stockstore.shared.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    @Column(nullable = false)
    private LocalDateTime saleDate;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private String shippingAddress;

    public Order(LocalDateTime saleDate, String shippingAddress){
        this.saleDate = saleDate;
        this.shippingAddress=shippingAddress;
    }
}
