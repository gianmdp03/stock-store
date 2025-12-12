package com.stockstore.stockstore.shared.mapper;

import com.stockstore.stockstore.shared.dto.inventoryitem.InventoryItemDetailDTO;
import com.stockstore.stockstore.shared.dto.inventoryitem.InventoryItemRequestDTO;
import com.stockstore.stockstore.shared.model.InventoryItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public abstract class InventoryItemMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", ignore = true)
    public abstract InventoryItem toEntity(InventoryItemRequestDTO dto);
    public abstract InventoryItemDetailDTO toDetailDTO(InventoryItem entity);
}
