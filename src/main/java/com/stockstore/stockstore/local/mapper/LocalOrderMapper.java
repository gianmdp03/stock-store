package com.stockstore.stockstore.local.mapper;

import com.stockstore.stockstore.local.dto.localOrder.LocalOrderDetailDTO;
import com.stockstore.stockstore.local.model.LocalOrder;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

@Mapper(componentModel = "spring")
public abstract class LocalOrderMapper {
    @Autowired
    @Lazy
    private LocalOrderItemMapper localOrderItemMapper;

    public abstract LocalOrderDetailDTO toDetailDTO(LocalOrder entity);
}
