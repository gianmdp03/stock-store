package com.stockstore.stockstore.local.controller;

import com.stockstore.stockstore.local.dto.localOrder.LocalOrderDetailDTO;
import com.stockstore.stockstore.local.dto.localOrderItem.LocalOrderItemRequestDTO;
import com.stockstore.stockstore.local.enums.PaymentMethod;
import com.stockstore.stockstore.local.service.LocalOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController("/api/local-orders")
@RequiredArgsConstructor
public class LocalOrderController {
    private final LocalOrderService localOrderService;

    @PostMapping("/{saleDate}/{paymentMethod}")
    public ResponseEntity<LocalOrderDetailDTO> addLocalOrderWithItems(@PathVariable LocalDateTime saleDate,
                                                                      @Valid @RequestBody List<LocalOrderItemRequestDTO> list,
                                                                      @PathVariable PaymentMethod paymentMethod){
        return ResponseEntity.status(HttpStatus.CREATED).body(localOrderService.addLocalOrderWithItems(saleDate, list, paymentMethod));
    }
}
