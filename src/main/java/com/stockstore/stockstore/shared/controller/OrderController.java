package com.stockstore.stockstore.shared.controller;

import com.stockstore.stockstore.shared.dto.order.OrderDetailDTO;
import com.stockstore.stockstore.shared.dto.order.OrderListDTO;
import com.stockstore.stockstore.shared.dto.order.OrderRequestDTO;
import com.stockstore.stockstore.shared.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDetailDTO> addOrder(@Valid @RequestBody OrderRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.addOrder(dto));
    }

    @GetMapping
    public ResponseEntity<Page<OrderListDTO>> listOrders(
            @PageableDefault(page = 0, size = 10, sort = "saleDate", direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.listOrders(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
