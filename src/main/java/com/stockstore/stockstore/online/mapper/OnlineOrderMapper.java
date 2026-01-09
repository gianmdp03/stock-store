package com.stockstore.stockstore.online.mapper;

import com.stockstore.stockstore.online.dto.order.OnlineOrderDetailDTO;
import com.stockstore.stockstore.online.model.OnlineOrder;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {OnlineOrderItemMapper.class})
public abstract class OnlineOrderMapper {
    public abstract OnlineOrderDetailDTO toDetailDto(OnlineOrder order);

}
