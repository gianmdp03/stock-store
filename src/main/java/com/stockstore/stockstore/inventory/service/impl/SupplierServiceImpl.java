package com.stockstore.stockstore.inventory.service.impl;

import com.stockstore.stockstore.exception.NotFoundException;
import com.stockstore.stockstore.inventory.dto.supplier.SupplierDetailDTO;
import com.stockstore.stockstore.inventory.dto.supplier.SupplierListDTO;
import com.stockstore.stockstore.inventory.dto.supplier.SupplierRequestDTO;
import com.stockstore.stockstore.inventory.mapper.SupplierMapper;
import com.stockstore.stockstore.inventory.model.Supplier;
import com.stockstore.stockstore.inventory.repository.SupplierRepository;
import com.stockstore.stockstore.inventory.service.SupplierService;
import com.stockstore.stockstore.shared.model.Category;
import jakarta.persistence.EntityNotFoundException;
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
        Supplier supplier = supplierMapper.toEntity(dto);
        Supplier savedSupplier = supplierRepository.save(supplier);

        return supplierMapper.toDetailDto(savedSupplier);
    }

    @Override
    public Page<SupplierListDTO> listSuppliers(Pageable pageable) {
        Page<Supplier> supplierPage = supplierRepository.findAll(pageable);
        if(supplierPage.isEmpty()){
            throw new NotFoundException("List is empty");
        }

        return supplierPage.map(supplierMapper::toListDto);
    }

    @Override
    @Transactional
    public void deleteSupplier(Long supplierId) {
        Supplier supplier = supplierRepository.findById(supplierId).orElseThrow(()-> new NotFoundException("Supplier ID does not exist"));

        supplierRepository.delete(supplier);
    }
}
