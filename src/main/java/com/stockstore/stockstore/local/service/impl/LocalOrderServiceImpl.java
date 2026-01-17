package com.stockstore.stockstore.local.service.impl;

import com.stockstore.stockstore.exception.BadRequestException;
import com.stockstore.stockstore.exception.ConflictException;
import com.stockstore.stockstore.exception.NotFoundException;
import com.stockstore.stockstore.local.dto.localOrder.LocalOrderDetailDTO;
import com.stockstore.stockstore.local.dto.localOrderItem.LocalOrderItemRequestDTO;
import com.stockstore.stockstore.local.enums.PaymentMethod;
import com.stockstore.stockstore.local.mapper.LocalOrderItemMapper;
import com.stockstore.stockstore.local.mapper.LocalOrderMapper;
import com.stockstore.stockstore.local.model.LocalOrder;
import com.stockstore.stockstore.local.model.LocalOrderItem;
import com.stockstore.stockstore.local.repository.LocalOrderItemRepository;
import com.stockstore.stockstore.local.repository.LocalOrderRepository;
import com.stockstore.stockstore.local.service.LocalOrderService;
import com.stockstore.stockstore.shared.model.InventoryItem;
import com.stockstore.stockstore.shared.model.Product;
import com.stockstore.stockstore.shared.repository.InventoryItemRepository;
import com.stockstore.stockstore.shared.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LocalOrderServiceImpl implements LocalOrderService {
    private final LocalOrderRepository localOrderRepository;
    private final LocalOrderItemRepository localOrderItemRepository;
    private final LocalOrderMapper localOrderMapper;
    private final LocalOrderItemMapper localOrderItemMapper;
    private final ProductRepository productRepository;
    private final InventoryItemRepository inventoryItemRepository;

    @Override
    @Transactional
    public LocalOrderDetailDTO addLocalOrderWithItems(LocalDateTime saleDate, List<LocalOrderItemRequestDTO> list, PaymentMethod paymentMethod) {
        if (list.isEmpty()) {
            throw new BadRequestException("LocalOrderItem List is empty");
        }

        BigDecimal totalAmount = BigDecimal.ZERO;
        LocalOrder localOrder = addLocalOrder(saleDate, paymentMethod);
        List<LocalOrderItem> orderItems = new ArrayList<>();

        for (LocalOrderItemRequestDTO request : list) {
            // 1. Buscamos el producto (Igual que antes)
            Product product = productRepository.findByIdAndEnabledTrue(request.productId())
                    .orElseThrow(() -> new NotFoundException("Product ID does not exist"));

            int quantityNeeded = request.quantity();
            List<InventoryItem> inventoryItems = inventoryItemRepository
                    .findByProductIdAndStockGreaterThanOrderByExpireDateAsc(product.getId(), 0);

            for (InventoryItem item : inventoryItems) {
                if (quantityNeeded <= 0) break;

                int availableInBatch = item.getStock();
                if (availableInBatch >= quantityNeeded) {
                    //Alcanza y sobra (o queda justo)
                    item.setStock(availableInBatch - quantityNeeded);
                    quantityNeeded = 0;
                } else {
                    //El lote no alcanza, lo vaciamos y seguimos necesitando
                    item.setStock(0);
                    quantityNeeded -= availableInBatch;
                }

                //Se guarda el cambio de stock de este lote específico
                inventoryItemRepository.save(item);
            }

            if (quantityNeeded > 0) {
                //Si todavía falta, excepción y Rollbac
                throw new ConflictException("Stock insuficiente para el producto: " + product.getName());
            }

            LocalOrderItem item = localOrderItemMapper.toEntity(request);
            item.setLocalOrder(localOrder);
            item.setPrice(product.getPrice());
            item.setProduct(product);

            BigDecimal lineTotal = item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
            totalAmount = totalAmount.add(lineTotal);

            orderItems.add(item);
        }

        localOrderItemRepository.saveAll(orderItems);
        localOrder = addTotalAmountToLocalOrder(localOrder.getId(), totalAmount);
        localOrder.setLocalOrderItems(orderItems);

        return localOrderMapper.toDetailDTO(localOrder);
    }

    @Override
    public Page<LocalOrderDetailDTO> listLocalOrders(Pageable pageable) {
        Page<LocalOrder> page = localOrderRepository.findAll(pageable);
        if(page.isEmpty()){
            return Page.empty();
        }
        return page.map(localOrderMapper::toDetailDTO);
    }

    @Override
    public LocalOrderDetailDTO getLocalOrderById(Long id) {
        LocalOrder localOrder = localOrderRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("LocalOrder ID does not exist"));
        return localOrderMapper.toDetailDTO(localOrder);
    }

    @Override
    public Page<LocalOrderDetailDTO> searchLocalOrders(LocalDate saleDate, Pageable pageable) {
        LocalDateTime saleDateA = saleDate.atStartOfDay();
        LocalDateTime saleDateB = saleDate.atTime(LocalTime.MAX);
        Page<LocalOrder> page = localOrderRepository.findBySaleDateBetween(saleDateA, saleDateB, pageable);
        if(page.isEmpty()){
            return Page.empty();
        }
        return page.map(localOrderMapper::toDetailDTO);
    }

    @Override
    public Page<LocalOrderDetailDTO> searchLocalOrdersBetween(LocalDate start, LocalDate end, Pageable pageable) {
        LocalDateTime startDate = start.atStartOfDay();
        LocalDateTime endDate = end.atTime(LocalTime.MAX);
        Page<LocalOrder> page = localOrderRepository.findBySaleDateBetween(startDate, endDate, pageable);
        if(page.isEmpty()){
            return Page.empty();
        }
        return page.map(localOrderMapper::toDetailDTO);
    }

    @Transactional
    private LocalOrder addLocalOrder(LocalDateTime saleDate, PaymentMethod paymentMethod){
        return localOrderRepository.save(new LocalOrder(saleDate, paymentMethod));
    }

    @Transactional
    private LocalOrder addTotalAmountToLocalOrder(Long localOrderId, BigDecimal totalAmount){
        LocalOrder localOrder = localOrderRepository.findById(localOrderId)
                .orElseThrow(()-> new NotFoundException("LocalOrder ID does not exist"));
        localOrder.setTotalAmount(totalAmount);
        return localOrderRepository.save(localOrder);
    }
}
