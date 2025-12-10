package com.stockstore.stockstore.shared.service.impl;

import com.stockstore.stockstore.shared.dto.inventoryitem.InventoryItemDetailDTO;
import com.stockstore.stockstore.shared.dto.inventoryitem.InventoryItemRequestDTO;
import com.stockstore.stockstore.shared.mapper.InventoryItemMapper;
import com.stockstore.stockstore.shared.repository.InventoryItemRepository;
import com.stockstore.stockstore.shared.service.InventoryItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InventoryItemServiceImpl implements InventoryItemService {
    private final InventoryItemRepository inventoryItemRepository;
    private final InventoryItemMapper inventoryItemMapper;

    @Override
    @Transactional
    public InventoryItemDetailDTO addInventoryItem(InventoryItemRequestDTO dto) {
       Product
    }

    @Override
    public Page<InventoryItemDetailDTO> listInventoryItems(Pageable pageable) {
        return null;
    }

    @Override
    @Transactional
    public void deleteInventoryItem(Long inventoryItemId) {

    }
}
