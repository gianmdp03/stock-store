package com.stockstore.stockstore.online.controller;

import com.stockstore.stockstore.online.dto.onlineOrder.OnlineOrderDetailDTO;
import com.stockstore.stockstore.online.dto.onlineOrderItem.OnlineOrderItemRequestDTO;
import com.stockstore.stockstore.online.service.OnlineOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/online-orders")
@RequiredArgsConstructor
public class OnlineOrderController {
    private final OnlineOrderService onlineOrderService;

    @PostMapping
    public ResponseEntity<OnlineOrderDetailDTO> addOrder(@RequestBody List<OnlineOrderItemRequestDTO> dtos, @PathVariable String shippingAddress) {
        return ResponseEntity.status(HttpStatus.CREATED).body(onlineOrderService.addOnlineOrderWithItems(dtos, shippingAddress));
    }

    @GetMapping
    public ResponseEntity<Page<OnlineOrderDetailDTO>> listOrders(
            @PageableDefault(page = 0, size = 10, sort = "saleDate", direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(onlineOrderService.listOnlineOrders(pageable));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<OnlineOrderDetailDTO> getOnlineOrderById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(onlineOrderService.getOnlineOrderById(id));
    }

    @GetMapping("/{saleDate}")
    public ResponseEntity<Page<OnlineOrderDetailDTO>> searchOrders(
            @PathVariable LocalDate saleDate,
            @PageableDefault(page = 0, size = 10, sort = "saleDate", direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(onlineOrderService.searchOnlineOrders(saleDate, pageable));
    }

    @GetMapping("/{startDate}/{endDate}")
    public ResponseEntity<Page<OnlineOrderDetailDTO>> searchOrdersBetween(
            @PathVariable LocalDate startDate,
            @PathVariable LocalDate endDate,
            @PageableDefault(page = 0, size = 10, sort = "saleDate", direction = Sort.Direction.DESC) Pageable pageable)
    {
        return ResponseEntity.status(HttpStatus.OK).body(onlineOrderService.searchOnlineOrdersBetween(startDate, endDate, pageable));
    }
}
