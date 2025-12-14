package com.stockstore.stockstore.shared.mapper;

import com.stockstore.stockstore.shared.dto.orderItem.OrderItemDetailDTO;
import com.stockstore.stockstore.shared.dto.orderItem.OrderItemRequestDTO;

import com.stockstore.stockstore.shared.model.OrderItem;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public abstract class OrderItemMapper {
    @Autowired
    @Lazy
    private OrderMapper orderMapper;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "order", ignore = true)
    public abstract OrderItem toEntity (OrderItemRequestDTO dto);
    public abstract OrderItemDetailDTO toDetailDto ( OrderItem orderItem);
}
