package com.stockstore.stockstore.online.mapper;

import com.stockstore.stockstore.online.dto.orderItem.OnlineOrderItemDetailDTO;
import com.stockstore.stockstore.online.dto.orderItem.OnlineOrderItemRequestDTO;
import com.stockstore.stockstore.online.model.OnlineOrderItem;
import com.stockstore.stockstore.shared.mapper.ProductMapper;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public abstract class OnlineOrderItemMapper {
    @Autowired
    @Lazy
    private OnlineOrderMapper orderMapper;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "onlineOrder", ignore = true)
    public abstract OnlineOrderItem toEntity (OnlineOrderItemRequestDTO dto);
    public abstract OnlineOrderItemDetailDTO toDetailDto (OnlineOrderItem orderItem);
}
