package com.stockstore.stockstore.shared.service.impl;

import com.stockstore.stockstore.exception.NotFoundException;
import com.stockstore.stockstore.shared.dto.inventoryitem.InventoryItemDetailDTO;
import com.stockstore.stockstore.shared.dto.inventoryitem.InventoryItemRequestDTO;
import com.stockstore.stockstore.shared.dto.inventoryitem.InventoryItemUpdateDTO;
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

import java.time.LocalDate;
import java.util.List;

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
        Product product = productRepository.findByIdAndEnabledTrue(dto.productId())
                .orElseThrow(()-> new NotFoundException("Product ID does not exist"));
        InventoryItem inventoryItem = inventoryItemMapper.toEntity(dto);
        inventoryItem.setProduct(product);
        inventoryItem = inventoryItemRepository.save(inventoryItem);
        return inventoryItemMapper.toDetailDTO(inventoryItem);
    }

    @Override
    @Transactional
    public InventoryItemDetailDTO updateInventoryItem(Long id, InventoryItemUpdateDTO dto){
        InventoryItem inventoryItem = inventoryItemRepository.findById(id).orElseThrow(()->new NotFoundException("InventoryItem ID does not exist"));
        inventoryItemMapper.updateEntityFromDto(dto, inventoryItem);
        inventoryItem = inventoryItemRepository.save(inventoryItem);
        return inventoryItemMapper.toDetailDTO(inventoryItem);
    }

    @Override
    public Page<InventoryItemDetailDTO> listInventoryItems(Pageable pageable) {
       Page<InventoryItem> page = inventoryItemRepository.findAll(pageable);
       if(page.isEmpty()){
           return Page.empty();
       }
       return page.map(inventoryItemMapper::toDetailDTO);
    }

    @Override
    public Page<InventoryItemDetailDTO> searchByProductName(String name, Pageable pageable) {
        Page<InventoryItem> items = inventoryItemRepository.findByProduct_NameContainingIgnoreCase(name, pageable);
        return items.map(inventoryItemMapper::toDetailDTO);
    }

    @Override
    public InventoryItemDetailDTO getInventoryItemById(Long id){
        InventoryItem inventoryItem = inventoryItemRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("InventoryItem ID does not exist"));
        return inventoryItemMapper.toDetailDTO(inventoryItem);
    }

    @Override
    @Transactional
    public void deleteInventoryItem(Long inventoryItemId) {
        InventoryItem inventoryItem = inventoryItemRepository.findById(inventoryItemId).orElseThrow(()-> new NotFoundException("InventoryItem ID does not exist"));
        inventoryItemRepository.delete(inventoryItem);
    }

    @Override
    public List<InventoryItemDetailDTO> getTopStockItems() {
        return inventoryItemRepository.findTop10ByOrderByStockDesc().stream()
                .map(inventoryItemMapper::toDetailDTO)
                .toList();
    }

    @Override
    public List<InventoryItemDetailDTO> getLowStockItems(int stockLimit) {
        return inventoryItemRepository.findByStockLessThan(stockLimit).stream()
                .map(inventoryItemMapper::toDetailDTO)
                .toList();
    }
}
