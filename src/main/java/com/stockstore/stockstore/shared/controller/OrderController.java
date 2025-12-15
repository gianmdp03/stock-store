package com.stockstore.stockstore.shared.controller;

import com.stockstore.stockstore.shared.dto.order.OrderDetailDTO;
import com.stockstore.stockstore.shared.service.OrderService;
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
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDetailDTO> addOrder() {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.addOrder());
    }

    @GetMapping
    public ResponseEntity<Page<OrderDetailDTO>> listOrders(
            @PageableDefault(page = 0, size = 10, sort = "saleDate", direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.listOrders(pageable));
    }

    @GetMapping("/{saleDate}")
    public ResponseEntity<Page<OrderDetailDTO>> searchOrders(
            @PathVariable LocalDate saleDate,
            @PageableDefault(page = 0, size = 10, sort = "saleDate", direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.searchOrders(saleDate, pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
