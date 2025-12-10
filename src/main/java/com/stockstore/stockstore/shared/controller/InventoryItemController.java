package com.stockstore.stockstore.shared.controller;

import com.stockstore.stockstore.shared.service.InventoryItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventory-items")
@RequiredArgsConstructor
public class InventoryItemController {
    private final InventoryItemService inventoryItemService;
}
