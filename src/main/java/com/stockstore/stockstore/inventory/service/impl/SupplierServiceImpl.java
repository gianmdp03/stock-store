package com.stockstore.stockstore.inventory.service.impl;

import com.stockstore.stockstore.exception.NotFoundException;
import com.stockstore.stockstore.inventory.dto.supplier.*;
import com.stockstore.stockstore.inventory.mapper.SupplierMapper;
import com.stockstore.stockstore.inventory.model.Supplier;
import com.stockstore.stockstore.inventory.model.SupplierOrder;
import com.stockstore.stockstore.inventory.repository.SupplierOrderRepository;
import com.stockstore.stockstore.inventory.repository.SupplierRepository;
import com.stockstore.stockstore.inventory.service.SupplierService;
import com.stockstore.stockstore.shared.model.Product;
import com.stockstore.stockstore.shared.repository.ProductRepository;
import com.stockstore.stockstore.shared.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;
    private final ProductRepository productRepository;
    private final EmailService emailService;
    private final SupplierOrderRepository supplierOrderRepository;

    @Override
    @Transactional
    public SupplierDetailDTO addSupplier(SupplierRequestDTO dto) {

        List<Product> products = productRepository.findAllByIdInAndEnabledTrue(dto.productIds());
        Optional<Supplier> optionalSupplier = supplierRepository.findByName(dto.name());
//        if(products.isEmpty()){
//            throw new NotFoundException("Product list is empty");
//        } por ahora queda asi

        if(optionalSupplier.isPresent()){
            Supplier existingSupplier = optionalSupplier.get();
            existingSupplier.setEnabled(true);
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
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Supplier ID does not exist"));

        supplierMapper.updateEntityFromDto(dto, supplier);

        if(dto.productIds() != null) {
            List<Product> products = productRepository.findAllByIdInAndEnabledTrue(dto.productIds());
            supplier.setProducts(products);
        }

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
    public SupplierDetailDTO getSupplierById(Long id){
        Supplier supplier = supplierRepository.findByIdAndEnabledTrue(id)
                .orElseThrow(()-> new NotFoundException("Supplier ID does not exist"));
        return supplierMapper.toDetailDto(supplier);
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

    @Override
    @Transactional
    public void sendOrderToSupplier(List<SupplierOrderDTO> items, Long supplierId) {
        Supplier supplier = supplierRepository.findByIdAndEnabledTrue(supplierId)
                .orElseThrow(() -> new NotFoundException("Supplier ID does not exist"));
        BigDecimal calculatedTotalCost = BigDecimal.ZERO;
        int calculatedTotalItems = 0;

        for (SupplierOrderDTO itemDto : items) {
            Product product = productRepository.findById(itemDto.productId())
                    .orElseThrow(() -> new NotFoundException("Product not found"));
            BigDecimal quantityBd = BigDecimal.valueOf(itemDto.quantity());
            BigDecimal itemSubtotal = product.getPrice().multiply(quantityBd);
            calculatedTotalCost = calculatedTotalCost.add(itemSubtotal);
            calculatedTotalItems += itemDto.quantity();
        }

        SupplierOrder order = SupplierOrder.builder()
                .supplier(supplier)
                .date(LocalDateTime.now())
                .totalItems(calculatedTotalItems)

                .totalCost(calculatedTotalCost.doubleValue())
                .status("PENDING")
                .build();

        supplierOrderRepository.save(order);

        StringBuilder body = new StringBuilder();

        body.append("Estimado proveedor ").append(supplier.getName()).append(",\n\n");

        body.append("ID de Pedido: #").append(order.getId()).append("\n");



        emailService.sendEmail(supplier.getEmail(), "Nuevo Pedido - " + LocalDate.now(), body.toString());
    }


    @Override
    public Page<SupplierOrderResponseDTO> getSupplierOrders(Pageable pageable) {
        return supplierOrderRepository.findAllByOrderByDateDesc(pageable)
                .map(order -> new SupplierOrderResponseDTO(
                        order.getId(),
                        order.getSupplier().getName(),
                        order.getDate(),
                        order.getTotalItems(),
                        order.getStatus(),
                        order.getTotalCost()
                ));
    }

    @Override
    @Transactional
    public void updateOrderStatus(Long orderId, String status) {
        SupplierOrder order = supplierOrderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order not found"));
        order.setStatus(status);
        supplierOrderRepository.save(order);
    }
}
