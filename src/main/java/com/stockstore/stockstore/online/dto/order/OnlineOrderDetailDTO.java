package com.stockstore.stockstore.online.dto.order;

import com.stockstore.stockstore.online.dto.orderItem.OnlineOrderItemDetailDTO;

import java.time.LocalDate;
import java.util.List;

public record OnlineOrderDetailDTO(Long id, LocalDate saleDate, List<OnlineOrderItemDetailDTO> onlineOrderItems) {}
