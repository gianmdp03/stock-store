package com.stockstore.stockstore.shared.controller;

import com.stockstore.stockstore.shared.dto.orderItem.OrderItemDetailDTO;
import com.stockstore.stockstore.shared.dto.orderItem.OrderItemRequestDTO;
import com.stockstore.stockstore.shared.service.OrderItemService;
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
@RequestMapping("/api/orders/items")
@RequiredArgsConstructor
public class OrderItemController {
    private final OrderItemService orderItemService;

    @PostMapping
    public ResponseEntity<OrderItemDetailDTO> addOrderItem(@Valid @RequestBody OrderItemRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderItemService.addOrderItem(dto));
    }

    @GetMapping
    public ResponseEntity<Page<OrderItemDetailDTO>> listOrderItems(
            @PageableDefault(page = 0, size = 10, sort = "amount", direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(orderItemService.listOrderItems(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItems(@PathVariable Long id){
        orderItemService.deleteOrderItem(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
