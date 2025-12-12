package com.stockstore.stockstore.shared.mapper;

import com.stockstore.stockstore.shared.dto.order.OrderDetailDTO;
import com.stockstore.stockstore.shared.dto.order.OrderListDTO;
import com.stockstore.stockstore.shared.dto.order.OrderRequestDTO;
import com.stockstore.stockstore.shared.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProductMapper.class, OrderItemMapper.class})
public abstract class OrderMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "products", ignore = true)
    public abstract Order toEntity(OrderRequestDTO dto);
    public abstract OrderDetailDTO toDetailDto(Order order);
    public abstract OrderListDTO toListDto(Order order);
}
