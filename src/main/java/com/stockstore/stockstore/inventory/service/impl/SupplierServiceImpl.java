package com.stockstore.stockstore.inventory.service.impl;

import com.stockstore.stockstore.inventory.dto.supplier.SupplierDetailDTO;
import com.stockstore.stockstore.inventory.dto.supplier.SupplierListDTO;
import com.stockstore.stockstore.inventory.dto.supplier.SupplierRequestDTO;
import com.stockstore.stockstore.inventory.mapper.SupplierMapper;
import com.stockstore.stockstore.inventory.repository.SupplierRepository;
import com.stockstore.stockstore.inventory.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    @Override
    @Transactional
    public SupplierDetailDTO addSupplier(SupplierRequestDTO dto) {
        return null;
    }

    @Override
    public Page<SupplierListDTO> listSuppliers(Pageable pageable) {
        return null;
    }

    @Override
    @Transactional
    public void deleteSupplier(Long supplierId) {

    }
}
