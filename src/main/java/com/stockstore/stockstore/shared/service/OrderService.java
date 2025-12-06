package com.stockstore.stockstore.shared.service;

import com.stockstore.stockstore.shared.dto.order.OrderDetailDTO;
import com.stockstore.stockstore.shared.dto.order.OrderListDTO;
import com.stockstore.stockstore.shared.dto.order.OrderRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    OrderDetailDTO addOrder(OrderRequestDTO dto);
    Page<OrderListDTO> listOrders(Pageable pageable);
    void deleteOrder(Long orderId);
}
