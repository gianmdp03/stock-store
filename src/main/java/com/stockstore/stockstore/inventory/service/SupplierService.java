package com.stockstore.stockstore.inventory.service;

import com.stockstore.stockstore.inventory.dto.supplier.SupplierDetailDTO;
import com.stockstore.stockstore.inventory.dto.supplier.SupplierListDTO;
import com.stockstore.stockstore.inventory.dto.supplier.SupplierRequestDTO;
import com.stockstore.stockstore.inventory.dto.supplier.SupplierUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SupplierService {
    SupplierDetailDTO addSupplier(SupplierRequestDTO dto);
    SupplierDetailDTO updateSupplier(Long id, SupplierUpdateDTO dto);
    Page<SupplierListDTO> listSuppliers(Pageable pageable);
    void deleteSupplier(Long supplierId);
}
