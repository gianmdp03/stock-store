package com.stockstore.stockstore.inventory.service.impl;

import com.stockstore.stockstore.exception.ConflictException;
import com.stockstore.stockstore.exception.NotFoundException;
import com.stockstore.stockstore.inventory.dto.supplier.SupplierDetailDTO;
import com.stockstore.stockstore.inventory.dto.supplier.SupplierListDTO;
import com.stockstore.stockstore.inventory.dto.supplier.SupplierRequestDTO;
import com.stockstore.stockstore.inventory.dto.supplier.SupplierUpdateDTO;
import com.stockstore.stockstore.inventory.mapper.SupplierMapper;
import com.stockstore.stockstore.inventory.model.Supplier;
import com.stockstore.stockstore.inventory.repository.SupplierRepository;
import com.stockstore.stockstore.inventory.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    @Override
    @Transactional
    public SupplierDetailDTO addSupplier(SupplierRequestDTO dto) {
        Supplier supplier = supplierRepository.save(supplierMapper.toEntity(dto));
        return supplierMapper.toDetailDto(supplier);
    }

    @Override
    @Transactional
    public SupplierDetailDTO updateSupplier(Long id, SupplierUpdateDTO dto){
        Supplier supplier = supplierRepository.findById(id).orElseThrow(()-> new NotFoundException("Supplier ID does not exist"));
        supplierMapper.updateEntityFromDto(dto, supplier);
        supplier = supplierRepository.save(supplier);
        return supplierMapper.toDetailDto(supplier);
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

    @Override
    public Page<SupplierListDTO> searchSuppliers(String email, Pageable page) {
        if(email == null || email.isBlank()){
            return Page.empty();
        }
      Page<Supplier> supplierPage = supplierRepository.findByEmailContainingIgnoreCase(email, page);
        return supplierPage.map(supplierMapper::toListDto);

    }
}
