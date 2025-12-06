package com.stockstore.stockstore.shared.service;

import com.stockstore.stockstore.shared.dto.inventoryitem.InventoryItemDetailDTO;
import com.stockstore.stockstore.shared.dto.inventoryitem.InventoryItemListDTO;
import com.stockstore.stockstore.shared.dto.inventoryitem.InventoryItemRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InventoryItemService {
    InventoryItemDetailDTO addInventoryItem(InventoryItemRequestDTO dto);
    Page<InventoryItemListDTO> listInventoryItems(Pageable pageable);
    void deleteInventoryItem(Long inventoryItemId);
}
