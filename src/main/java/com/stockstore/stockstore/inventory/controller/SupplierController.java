package com.stockstore.stockstore.inventory.controller;

import com.stockstore.stockstore.inventory.dto.supplier.SupplierDetailDTO;
import com.stockstore.stockstore.inventory.dto.supplier.SupplierListDTO;
import com.stockstore.stockstore.inventory.dto.supplier.SupplierRequestDTO;
import com.stockstore.stockstore.inventory.dto.supplier.SupplierUpdateDTO;
import com.stockstore.stockstore.inventory.service.SupplierOrderService;
import com.stockstore.stockstore.inventory.service.SupplierService;
import com.stockstore.stockstore.shared.dto.Batch.BatchRequestDTO;
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
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
public class SupplierController {
    private final SupplierService supplierService;
    private final SupplierOrderService supplierOrderService;

    @PostMapping
    public ResponseEntity<SupplierDetailDTO> addSupplier(@Valid @RequestBody SupplierRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(supplierService.addSupplier(dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SupplierDetailDTO> updateSupplier(@PathVariable Long id, @Valid @RequestBody SupplierUpdateDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(supplierService.updateSupplier(id, dto));
    }

    @GetMapping
    public ResponseEntity<Page<SupplierListDTO>> listSuppliers(
            @PageableDefault(page = 0, size = 10, sort = "name", direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(supplierService.listSuppliers(pageable));
    }

    @GetMapping("/{name}")
    public ResponseEntity<Page<SupplierListDTO>> searchSuppliersByName(
            @PathVariable String name,
            @PageableDefault(page = 0, size = 10, sort = "name", direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(supplierService.searchSuppliersByName(name, pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id){
        supplierService.deleteSupplier(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{email}")
    public ResponseEntity<Page<SupplierListDTO>> searchSuppliers(
            @PathVariable String email, @PageableDefault(page=0, size = 10, sort="name", direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(supplierService.searchSuppliers(email, pageable));
    }


    @PostMapping("/{id}/order")
    public ResponseEntity<String> orderToSupplier(@PathVariable Long id, @Valid @RequestBody BatchRequestDTO dto) {
        supplierOrderService.processSupplierOrder(id, dto);
        return ResponseEntity.ok("Pedido enviado correctamente al proveedor");
    }
}
