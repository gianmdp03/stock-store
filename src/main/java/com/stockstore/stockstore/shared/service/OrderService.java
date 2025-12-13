package com.stockstore.stockstore.shared.service;

import com.stockstore.stockstore.shared.dto.order.OrderDetailDTO;
import com.stockstore.stockstore.shared.dto.order.OrderUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    OrderDetailDTO addOrder();
    OrderDetailDTO updateOrder(Long id, OrderUpdateDTO dto);
    Page<OrderDetailDTO> listOrders(Pageable pageable);
    void deleteOrder(Long orderId);
}
