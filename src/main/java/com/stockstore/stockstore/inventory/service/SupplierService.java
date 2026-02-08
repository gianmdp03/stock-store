package com.stockstore.stockstore.inventory.service;

import com.stockstore.stockstore.inventory.dto.supplier.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SupplierService {
    SupplierDetailDTO addSupplier(SupplierRequestDTO dto);
    SupplierDetailDTO updateSupplier(Long id, SupplierUpdateDTO dto);
    Page<SupplierListDTO> listSuppliers(Pageable pageable);
    Page<SupplierListDTO> searchSuppliersByName(String name, Pageable pageable);
    SupplierDetailDTO getSupplierById(Long id);
    void deleteSupplier(Long supplierId);
    Page<SupplierListDTO> searchSuppliers(String email, Pageable page);
    void sendOrderToSupplier(List<SupplierOrderDTO> items, Long supplierId);
    Page<SupplierOrderResponseDTO> getSupplierOrders(Pageable pageable);
    void updateOrderStatus(Long orderId, String status);
}

