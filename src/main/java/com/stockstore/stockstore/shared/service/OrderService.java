package com.stockstore.stockstore.shared.service;


import com.stockstore.stockstore.shared.dto.order.OrderDetailDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface OrderService {
    OrderDetailDTO addOrder();
    Page<OrderDetailDTO> listOrders(Pageable pageable);
    Page<OrderDetailDTO> searchOrders(LocalDate saleDate, Pageable pageable);
}
