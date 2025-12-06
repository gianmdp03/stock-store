package com.stockstore.stockstore.inventory.service;

import com.stockstore.stockstore.inventory.dto.supplier.SupplierDetailDTO;
import com.stockstore.stockstore.inventory.dto.supplier.SupplierListDTO;
import com.stockstore.stockstore.inventory.dto.supplier.SupplierRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SupplierService {
    SupplierDetailDTO addSupplier(SupplierRequestDTO dto);
    Page<SupplierListDTO> listSuppliers(Pageable pageable);
    void deleteSupplier(Long supplierId);
}
