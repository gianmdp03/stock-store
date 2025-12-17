package com.stockstore.stockstore.inventory.service;

import com.stockstore.stockstore.inventory.dto.supplier.SupplierDetailDTO;
import com.stockstore.stockstore.inventory.dto.supplier.SupplierListDTO;
import com.stockstore.stockstore.inventory.dto.supplier.SupplierRequestDTO;
import com.stockstore.stockstore.inventory.dto.supplier.SupplierUpdateDTO;
import com.stockstore.stockstore.shared.dto.Batch.BatchRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SupplierService {
    SupplierDetailDTO addSupplier(SupplierRequestDTO dto);
    SupplierDetailDTO updateSupplier(Long id, SupplierUpdateDTO dto);
    Page<SupplierListDTO> listSuppliers(Pageable pageable);
    Page<SupplierListDTO> searchSuppliersByName(String name, Pageable pageable);
    void deleteSupplier(Long supplierId);
    Page<SupplierListDTO> searchSuppliers(String email, Pageable page);

}
