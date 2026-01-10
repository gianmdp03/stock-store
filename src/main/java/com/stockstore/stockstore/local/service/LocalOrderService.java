package com.stockstore.stockstore.local.service;

import com.stockstore.stockstore.local.dto.localOrder.LocalOrderDetailDTO;
import com.stockstore.stockstore.local.dto.localOrderItem.LocalOrderItemRequestDTO;
import com.stockstore.stockstore.local.enums.PaymentMethod;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface LocalOrderService {
    LocalOrderDetailDTO addLocalOrderWithItems(LocalDateTime saleDate, List<LocalOrderItemRequestDTO> list, PaymentMethod paymentMethod);
    Page<LocalOrderDetailDTO> listLocalOrders(Pageable pageable);
    LocalOrderDetailDTO getLocalOrderById(Long id);
    Page<LocalOrderDetailDTO> searchLocalOrders(LocalDate saleDate, Pageable pageable);
    Page<LocalOrderDetailDTO> searchLocalOrdersBetween(LocalDate start, LocalDate end, Pageable pageable);
}
