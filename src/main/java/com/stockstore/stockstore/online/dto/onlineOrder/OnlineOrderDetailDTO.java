package com.stockstore.stockstore.online.dto.onlineOrder;

import com.stockstore.stockstore.online.dto.onlineOrderItem.OnlineOrderItemDetailDTO;

import java.time.LocalDate;
import java.util.List;

public record OnlineOrderDetailDTO(Long id, LocalDate saleDate, List<OnlineOrderItemDetailDTO> onlineOrderItems) {}
