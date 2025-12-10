package com.stockstore.stockstore.shared.controller;

import com.stockstore.stockstore.shared.dto.inventoryitem.InventoryItemDetailDTO;
import com.stockstore.stockstore.shared.dto.inventoryitem.InventoryItemRequestDTO;
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

@RestController
@RequestMapping("/api/inventory-items")
@RequiredArgsConstructor
public class InventoryItemController {
    private final InventoryItemService inventoryItemService;

    @PostMapping
    public ResponseEntity<InventoryItemDetailDTO> addInventoryItem(@Valid @RequestBody InventoryItemRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryItemService.addInventoryItem(dto));
    }

    @GetMapping
    public ResponseEntity<Page<InventoryItemDetailDTO>> listInventoryItems(
            @PageableDefault(page = 0, size = 10, sort = "expireDate", direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(inventoryItemService.listInventoryItems(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventoryItem(@PathVariable Long id){
        inventoryItemService.deleteInventoryItem(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
