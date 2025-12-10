package com.stockstore.stockstore.shared.mapper;

import com.stockstore.stockstore.shared.dto.inventoryitem.InventoryItemDetailDTO;
import com.stockstore.stockstore.shared.dto.inventoryitem.InventoryItemRequestDTO;
import com.stockstore.stockstore.shared.model.InventoryItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface InventoryItemMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", ignore = true)
    InventoryItem toEntity(InventoryItemRequestDTO dto);
    InventoryItemDetailDTO toDetailDTO(InventoryItem entity);
}
