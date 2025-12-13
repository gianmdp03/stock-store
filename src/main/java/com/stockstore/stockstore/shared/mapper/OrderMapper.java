package com.stockstore.stockstore.shared.mapper;

import com.stockstore.stockstore.shared.dto.order.OrderDetailDTO;
import com.stockstore.stockstore.shared.dto.order.OrderUpdateDTO;
import com.stockstore.stockstore.shared.model.Order;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {ProductMapper.class, OrderItemMapper.class})
public abstract class OrderMapper {
    public abstract OrderDetailDTO toDetailDto(Order order);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    public abstract void updateEntityFromDto(OrderUpdateDTO dto, @MappingTarget Order entity);
}
