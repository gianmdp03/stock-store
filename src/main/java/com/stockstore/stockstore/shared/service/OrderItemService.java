package com.stockstore.stockstore.shared.service;

import com.stockstore.stockstore.shared.dto.orderItem.OrderItemDetailDTO;
import com.stockstore.stockstore.shared.dto.orderItem.OrderItemListDTO;
import com.stockstore.stockstore.shared.dto.orderItem.OrderItemRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderItemService {
    OrderItemDetailDTO addOrderItem(OrderItemRequestDTO dto);
    Page<OrderItemListDTO> listOrderItems(Pageable pageable);
    void deleteOrderItem(Long orderItemId);
}
