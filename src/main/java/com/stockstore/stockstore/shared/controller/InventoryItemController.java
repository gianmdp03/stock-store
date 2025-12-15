package com.stockstore.stockstore.shared.controller;

import com.stockstore.stockstore.shared.dto.inventoryitem.InventoryItemDetailDTO;
import com.stockstore.stockstore.shared.dto.inventoryitem.InventoryItemRequestDTO;
import com.stockstore.stockstore.shared.dto.inventoryitem.InventoryItemUpdateDTO;
import com.stockstore.stockstore.shared.service.InventoryItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/inventory-items")
@RequiredArgsConstructor
public class InventoryItemController {
    private final InventoryItemService inventoryItemService;

    @PostMapping
    public ResponseEntity<InventoryItemDetailDTO> addInventoryItem(@Valid @RequestBody InventoryItemRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryItemService.addInventoryItem(dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<InventoryItemDetailDTO> updateInventoryItem(@PathVariable Long id, @Valid @RequestBody InventoryItemUpdateDTO dto)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryItemService.updateInventoryItem(id, dto));
    }

    @GetMapping
    public ResponseEntity<Page<InventoryItemDetailDTO>> listInventoryItems(
            @PageableDefault(page = 0, size = 10, sort = "expireDate", direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(inventoryItemService.listInventoryItems(pageable));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Page<InventoryItemDetailDTO>> searchInventoryItemByProduct(
            @PathVariable Long productId,
            @PageableDefault(page = 0, size = 10, sort = "expireDate", direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(inventoryItemService.searchInventoryItemsByProduct(productId, pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventoryItem(@PathVariable Long id){
        inventoryItemService.deleteInventoryItem(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @GetMapping("/{expireDate}")
    public ResponseEntity<Page<InventoryItemDetailDTO>> searchInventoryItem(
            @PathVariable LocalDate expireDate, @PageableDefault(page = 0, size = 10, sort = "expireDate", direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(inventoryItemService.searchInventoryItem(pageable, expireDate));
    }
}
