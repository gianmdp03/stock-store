package com.stockstore.stockstore.local.dto.localOrder;

import com.stockstore.stockstore.local.dto.localOrderItem.LocalOrderItemDetailDTO;
import com.stockstore.stockstore.local.enums.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record LocalOrderDetailDTO(Long id,
                                  LocalDateTime saleDate,
                                  List<LocalOrderItemDetailDTO> localOrderItems,
                                  PaymentMethod paymentMethod,
                                  BigDecimal totalAmount){}
