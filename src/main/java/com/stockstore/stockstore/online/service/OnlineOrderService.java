package com.stockstore.stockstore.online.service;

import com.stockstore.stockstore.online.dto.onlineOrder.OnlineOrderDetailDTO;
import com.stockstore.stockstore.online.dto.onlineOrderItem.OnlineOrderItemRequestDTO;
import com.stockstore.stockstore.online.model.OnlineOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface OnlineOrderService {
    OnlineOrderDetailDTO addOnlineOrderWithItems(LocalDateTime saleDate, List<OnlineOrderItemRequestDTO> onlineOrderItemsDTO);
    Page<OnlineOrderDetailDTO> listOnlineOrders(Pageable pageable);
    OnlineOrderDetailDTO getOnlineOrderById(Long id);
    Page<OnlineOrderDetailDTO> searchOnlineOrders(LocalDate saleDate, Pageable pageable);
    Page<OnlineOrderDetailDTO> searchOnlineOrdersBetween(LocalDate start, LocalDate end, Pageable pageable);
}
