package com.stockstore.stockstore.shared.service;

import com.stockstore.stockstore.shared.dto.orderItem.OrderItemDetailDTO;
import com.stockstore.stockstore.shared.dto.orderItem.OrderItemRequestDTO;
import com.stockstore.stockstore.shared.dto.orderItem.OrderItemUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderItemService {
    OrderItemDetailDTO addOrderItem(OrderItemRequestDTO dto);
    OrderItemDetailDTO updateOrderItem(Long id, OrderItemUpdateDTO dto);
    Page<OrderItemDetailDTO> listOrderItems(Pageable pageable);
    void deleteOrderItem(Long orderItemId);
}
