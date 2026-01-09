package com.stockstore.stockstore.online.service;

import com.stockstore.stockstore.online.dto.order.OnlineOrderDetailDTO;
import com.stockstore.stockstore.online.dto.orderItem.OnlineOrderItemRequestDTO;
import com.stockstore.stockstore.online.model.OnlineOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface OnlineOrderService {
    OnlineOrder addOnlineOrder(String shippingAdress);
    OnlineOrderDetailDTO addOnlineOrderWithItems(List<OnlineOrderItemRequestDTO> onlineOrderItemsDTO, String shippingAddress);
    Page<OnlineOrderDetailDTO> listOnlineOrders(Pageable pageable);
    Page<OnlineOrderDetailDTO> searchOnlineOrders(LocalDate saleDate, Pageable pageable);
    Page<OnlineOrderDetailDTO> searchOnlineOrdersBetween(LocalDate start, LocalDate end, Pageable pageable);
}
