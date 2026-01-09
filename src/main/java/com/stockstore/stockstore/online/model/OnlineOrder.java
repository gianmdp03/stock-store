package com.stockstore.stockstore.online.model;

import com.stockstore.stockstore.online.enums.OnlineOrderStatus;
import com.stockstore.stockstore.security.user.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "online_orders")
@Getter
@Setter
@NoArgsConstructor
public class OnlineOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime saleDate;

    @OneToMany(mappedBy = "onlineOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OnlineOrderItem> onlineOrderItems = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private OnlineOrderStatus status;

    private String shippingAddress;

    public OnlineOrder(LocalDateTime saleDate, String shippingAddress){
        this.saleDate = saleDate;
        this.shippingAddress=shippingAddress;
    }
}
