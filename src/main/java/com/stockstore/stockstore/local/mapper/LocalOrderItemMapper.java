package com.stockstore.stockstore.local.mapper;

import com.stockstore.stockstore.local.dto.localOrderItem.LocalOrderItemRequestDTO;
import com.stockstore.stockstore.local.model.LocalOrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {LocalOrderMapper.class})
public abstract class LocalOrderItemMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "localOrder", ignore = true)
    public abstract LocalOrderItem toEntity(LocalOrderItemRequestDTO dto);
}
