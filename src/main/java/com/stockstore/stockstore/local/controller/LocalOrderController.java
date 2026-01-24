package com.stockstore.stockstore.local.controller;

import com.stockstore.stockstore.local.dto.localOrder.LocalOrderDetailDTO;
import com.stockstore.stockstore.local.dto.localOrderItem.LocalOrderItemRequestDTO;
import com.stockstore.stockstore.local.enums.PaymentMethod;
import com.stockstore.stockstore.local.service.LocalOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/local-orders")
@RequiredArgsConstructor
public class LocalOrderController {
    private final LocalOrderService localOrderService;

    @PostMapping("/{saleDate}/{paymentMethod}")
    public ResponseEntity<LocalOrderDetailDTO> addLocalOrderWithItems(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime saleDate,
                                                                      @PathVariable String paymentMethod,
                                                                      @Valid @RequestBody List<LocalOrderItemRequestDTO> list){
        return ResponseEntity.status(HttpStatus.CREATED).body(localOrderService.addLocalOrderWithItems(saleDate, list, PaymentMethod.fromStringOrDefault(paymentMethod)));
    }

    @GetMapping
    public ResponseEntity<Page<LocalOrderDetailDTO>> listLocalOrders(
            @PageableDefault(page = 0, size = 10, sort = "saleDate", direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(localOrderService.listLocalOrders(pageable));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<LocalOrderDetailDTO> getLocalOrderById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(localOrderService.getLocalOrderById(id));
    }

    @GetMapping("/{saleDate}")
    public ResponseEntity<Page<LocalOrderDetailDTO>> searchLocalOrders(@PathVariable LocalDate saleDate,
                                                                 @PageableDefault(page = 0, size = 10, sort = "saleDate", direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(localOrderService.searchLocalOrders(saleDate, pageable));
    }

    @GetMapping("/{startDate}/{endDate}")
    public ResponseEntity<Page<LocalOrderDetailDTO>> searchLocalOrdersBetween(@PathVariable LocalDate startDate,
                                                                              @PathVariable LocalDate endDate,
                                                                              @PageableDefault(page = 0, size = 10, sort = "saleDate", direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(localOrderService.searchLocalOrdersBetween(startDate, endDate, pageable));
    }
}
