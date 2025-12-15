package com.stockstore.stockstore.shared.service;

import com.stockstore.stockstore.shared.dto.inventoryitem.InventoryItemDetailDTO;
import com.stockstore.stockstore.shared.dto.inventoryitem.InventoryItemRequestDTO;
import com.stockstore.stockstore.shared.dto.inventoryitem.InventoryItemUpdateDTO;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface InventoryItemService {
    InventoryItemDetailDTO addInventoryItem(InventoryItemRequestDTO dto);
    InventoryItemDetailDTO updateInventoryItem(Long id, InventoryItemUpdateDTO dto);
    Page<InventoryItemDetailDTO> listInventoryItems(Pageable pageable);
    void deleteInventoryItem(Long inventoryItemId);
    Page<InventoryItemDetailDTO> searchInventoryItem(Pageable page, LocalDate date);
}
