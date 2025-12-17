package com.stockstore.stockstore.inventory.service.impl;

import com.stockstore.stockstore.exception.NotFoundException;
import com.stockstore.stockstore.inventory.dto.supplier.SupplierDetailDTO;
import com.stockstore.stockstore.inventory.dto.supplier.SupplierListDTO;
import com.stockstore.stockstore.inventory.dto.supplier.SupplierRequestDTO;
import com.stockstore.stockstore.inventory.dto.supplier.SupplierUpdateDTO;
import com.stockstore.stockstore.inventory.mapper.SupplierMapper;
import com.stockstore.stockstore.inventory.model.Supplier;
import com.stockstore.stockstore.inventory.repository.SupplierRepository;
import com.stockstore.stockstore.inventory.service.SupplierService;
import com.stockstore.stockstore.shared.dto.Batch.BatchRequestDTO;
import com.stockstore.stockstore.shared.model.Product;
import com.stockstore.stockstore.shared.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;
    private final ProductRepository productRepository;


    @Override
    @Transactional
    public SupplierDetailDTO addSupplier(SupplierRequestDTO dto) {
        List<Product> products = productRepository.findByIdInAndEnabledTrue(dto.productIds());
        Optional<Supplier> optionalSupplier = supplierRepository.findByName(dto.name());

        if(products.isEmpty()){
            throw new NotFoundException("Product list is empty");
        }

        if(optionalSupplier.isPresent()){
            Supplier existingSupplier = optionalSupplier.get();

            // Lógica actual: solo reactiva
            existingSupplier.setEnabled(true);

            // CORRECCIÓN: Actualiza los datos con los del DTO
            existingSupplier.setEmail(dto.email());
            existingSupplier.setPhoneNumber(dto.phoneNumber());
            existingSupplier.setProducts(products);

            existingSupplier = supplierRepository.save(existingSupplier);
            return supplierMapper.toDetailDto(existingSupplier);
        }

        Supplier supplier = supplierMapper.toEntity(dto);
        supplier.setProducts(products);
        supplier = supplierRepository.save(supplier);

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
        Page<Supplier> supplierPage = supplierRepository.findByEnabledTrue(pageable);
        if(supplierPage.isEmpty()){
            throw new NotFoundException("List is empty");
        }

        return supplierPage.map(supplierMapper::toListDto);
    }

    @Override
    public Page<SupplierListDTO> searchSuppliersByName(String name, Pageable pageable){
        if(name == null || name.isBlank()){
            return Page.empty();
        }
        Page<Supplier> page = supplierRepository.findAllByNameContainingIgnoreCaseAndEnabledTrue(name, pageable);
        return page.map(supplierMapper::toListDto);
    }

    @Override
    @Transactional
    public void deleteSupplier(Long supplierId) {
        Supplier supplier = supplierRepository.findByIdAndEnabledTrue(supplierId).orElseThrow(()-> new NotFoundException("Supplier ID does not exist"));
        supplierRepository.delete(supplier);
    }

    @Override
    public Page<SupplierListDTO> searchSuppliers(String email, Pageable page) {
        if(email == null || email.isBlank()){
            return Page.empty();
        }
      Page<Supplier> supplierPage = supplierRepository.findByEmailContainingIgnoreCaseAndEnabledTrue(email, page);
        return supplierPage.map(supplierMapper::toListDto);

    }

}
