package com.stockstore.stockstore.shared.mapper;

import com.stockstore.stockstore.shared.dto.order.OrderDetailDTO;
import com.stockstore.stockstore.shared.model.Order;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {OrderItemMapper.class})
public abstract class OrderMapper {
    public abstract OrderDetailDTO toDetailDto(Order order);

}
