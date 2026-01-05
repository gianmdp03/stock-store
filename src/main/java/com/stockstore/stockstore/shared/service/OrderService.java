package com.stockstore.stockstore.shared.service;


import com.stockstore.stockstore.shared.dto.order.OrderDetailDTO;

import com.stockstore.stockstore.shared.dto.orderItem.OrderItemRequestDTO;
import com.stockstore.stockstore.shared.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    Order addOrder(String shippingAdress);
    OrderDetailDTO addOrderWithItems(List<OrderItemRequestDTO> orderItemsDTO, String shippingAddress);
    Page<OrderDetailDTO> listOrders(Pageable pageable);
    Page<OrderDetailDTO> searchOrders(LocalDate saleDate, Pageable pageable);
    Page<OrderDetailDTO> searchOrdersBetween(LocalDate start, LocalDate end, Pageable pageable);
}
