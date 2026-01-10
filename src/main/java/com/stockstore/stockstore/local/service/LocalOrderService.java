package com.stockstore.stockstore.local.service;

import com.stockstore.stockstore.local.dto.localOrder.LocalOrderDetailDTO;
import com.stockstore.stockstore.local.dto.localOrderItem.LocalOrderItemRequestDTO;
import com.stockstore.stockstore.local.enums.PaymentMethod;
import com.stockstore.stockstore.local.model.LocalOrder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface LocalOrderService {
    LocalOrder addLocalOrder(LocalDateTime saleDate, PaymentMethod paymentMethod);
    LocalOrder addTotalAmountToLocalOrder(Long localOrderId, BigDecimal totalAmount);
    LocalOrderDetailDTO addLocalOrderWithItems(LocalDateTime saleDate, List<LocalOrderItemRequestDTO> list, PaymentMethod paymentMethod);
}
