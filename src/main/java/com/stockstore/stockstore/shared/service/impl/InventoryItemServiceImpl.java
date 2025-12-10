package com.stockstore.stockstore.shared.service.impl;

import com.stockstore.stockstore.exception.NotFoundException;
import com.stockstore.stockstore.shared.dto.inventoryitem.InventoryItemDetailDTO;
import com.stockstore.stockstore.shared.dto.inventoryitem.InventoryItemRequestDTO;
import com.stockstore.stockstore.shared.mapper.InventoryItemMapper;
import com.stockstore.stockstore.shared.model.InventoryItem;
import com.stockstore.stockstore.shared.model.Product;
import com.stockstore.stockstore.shared.repository.InventoryItemRepository;
import com.stockstore.stockstore.shared.repository.ProductRepository;
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
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public InventoryItemDetailDTO addInventoryItem(InventoryItemRequestDTO dto) {
        Product product = productRepository.findById(dto.productId())
                .orElseThrow(()-> new NotFoundException("Product not found"));
        InventoryItem inventoryItem = inventoryItemRepository.save(inventoryItemMapper.toEntity(dto));
        return inventoryItemMapper.toDetailDTO(inventoryItem);
    }

    @Override
    public Page<InventoryItemDetailDTO> listInventoryItems(Pageable pageable) {
       Page<Inven>
    }

    @Override
    @Transactional
    public void deleteInventoryItem(Long inventoryItemId) {

    }
}
