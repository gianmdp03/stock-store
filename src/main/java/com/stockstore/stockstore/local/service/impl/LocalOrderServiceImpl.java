package com.stockstore.stockstore.local.service.impl;

import com.stockstore.stockstore.exception.BadRequestException;
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
import com.stockstore.stockstore.shared.model.Product;
import com.stockstore.stockstore.shared.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    @Override
    @Transactional
    public LocalOrder addLocalOrder(LocalDateTime saleDate, PaymentMethod paymentMethod){
        return localOrderRepository.save(new LocalOrder(saleDate, paymentMethod));
    }

    @Override
    @Transactional
    public LocalOrderDetailDTO addLocalOrderWithItems(LocalDateTime saleDate, List<LocalOrderItemRequestDTO> list, PaymentMethod paymentMethod) {
        if(list.isEmpty()){
            throw new BadRequestException("LocalOrderItem List is empty");
        }
        LocalOrder localOrder = addLocalOrder(saleDate, paymentMethod);
        List<LocalOrderItem> orderItems = new ArrayList<>();
        for(LocalOrderItemRequestDTO request: list){
            LocalOrderItem item = localOrderItemMapper.toEntity(request);
            item.setLocalOrder(localOrder);
            Product product = productRepository.findByIdAndEnabledTrue(request.productId())
                    .orElseThrow(()-> new NotFoundException("Product ID does not exist"));
            item.setProduct(product);
            orderItems.add(item);
        }
        localOrderItemRepository.saveAll(orderItems);
        localOrder = localOrderRepository.findById(localOrder.getId())
                .orElseThrow(()-> new NotFoundException("LocalOrder ID does not exist"));
        return localOrderMapper.toDetailDTO(localOrder);
    }
}
