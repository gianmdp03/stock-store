package com.stockstore.stockstore.online.service.impl;

import com.stockstore.stockstore.exception.NotFoundException;
import com.stockstore.stockstore.local.model.LocalOrder;
import com.stockstore.stockstore.online.dto.onlineOrder.OnlineOrderDetailDTO;
import com.stockstore.stockstore.online.dto.onlineOrderItem.OnlineOrderItemRequestDTO;
import com.stockstore.stockstore.online.enums.OnlineOrderStatus;
import com.stockstore.stockstore.online.mapper.OnlineOrderItemMapper;
import com.stockstore.stockstore.online.mapper.OnlineOrderMapper;
import com.stockstore.stockstore.online.model.OnlineOrder;
import com.stockstore.stockstore.online.model.OnlineOrderItem;
import com.stockstore.stockstore.online.repository.OnlineOrderItemRepository;
import com.stockstore.stockstore.online.repository.OnlineOrderRepository;
import com.stockstore.stockstore.online.service.OnlineOrderService;
import com.stockstore.stockstore.security.user.model.User;
import com.stockstore.stockstore.security.user.repository.UserRepository;
import com.stockstore.stockstore.shared.model.Product;
import com.stockstore.stockstore.shared.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class OnlineOrderServiceImpl implements OnlineOrderService {
    private final OnlineOrderRepository onlineOrderRepository;
    private final OnlineOrderMapper onlineOrderMapper;
    private final OnlineOrderItemMapper onlineOrderItemMapper;
    private final ProductRepository productRepository;
    private final OnlineOrderItemRepository onlineOrderItemRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public OnlineOrderDetailDTO addOnlineOrderWithItems(List<OnlineOrderItemRequestDTO> onlineOrderItemsDTO, String shippingAddress){
        OnlineOrder order = addOnlineOrder(shippingAddress);
        if(onlineOrderItemsDTO.isEmpty()){
            throw new NotFoundException("OrderItem list is empty");
        }
        BigDecimal totalAmount = BigDecimal.ZERO;
        List<OnlineOrderItem> orderItems = new ArrayList<>();
        for(OnlineOrderItemRequestDTO dto : onlineOrderItemsDTO){
            OnlineOrderItem item = onlineOrderItemMapper.toEntity(dto);
            item.setOnlineOrder(order);
            Product product = productRepository.findByIdAndEnabledTrue(dto.productId()).orElseThrow(()->
                    new NotFoundException("Product ID does not exist"));
            item.setProduct(product);
            item.setPrice(product.getPrice());
            BigDecimal lineTotal = item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
            totalAmount = totalAmount.add(lineTotal);
            orderItems.add(item);
        }
        onlineOrderItemRepository.saveAll(orderItems);
        order = addTotalAmountToOnlineOrder(order.getId(), totalAmount);
        return onlineOrderMapper.toDetailDto(order);
    }

    @Override
    public Page<OnlineOrderDetailDTO> listOnlineOrders(Pageable pageable) {
        Page<OnlineOrder> page = onlineOrderRepository.findAll(pageable);
        if(page.isEmpty())
            throw new NotFoundException("Order list is empty");
        return page.map(onlineOrderMapper::toDetailDto);
    }
    @Override
    public OnlineOrderDetailDTO getOnlineOrderById(Long id){
        OnlineOrder onlineOrder = onlineOrderRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("OnlineOrder ID does not exist"));
        return onlineOrderMapper.toDetailDto(onlineOrder);
    }
    @Override
    public Page<OnlineOrderDetailDTO> searchOnlineOrders(LocalDate saleDate, Pageable pageable){
        LocalDateTime saleDateA = saleDate.atStartOfDay();
        LocalDateTime saleDateB = saleDate.atTime(LocalTime.MAX);
        Page<OnlineOrder> page = onlineOrderRepository.findBySaleDateBetween(saleDateA, saleDateB, pageable);
        if(page.isEmpty()){
            return Page.empty();
        }
        return page.map(onlineOrderMapper::toDetailDto);
    }

    @Override
    public Page<OnlineOrderDetailDTO> searchOnlineOrdersBetween(LocalDate start, LocalDate end, Pageable pageable) {
        LocalDateTime startDate = start.atStartOfDay();
        LocalDateTime endDate = end.atTime(LocalTime.MAX);
        Page<OnlineOrder> page = onlineOrderRepository.findBySaleDateBetween(startDate, endDate, pageable);
        if(page.isEmpty()){
            throw new NotFoundException("List is empty");
        }
        return page.map(onlineOrderMapper::toDetailDto);
    }

    @Transactional
    public OnlineOrder addOnlineOrder(String shippingAddress) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));

        OnlineOrder onlineOrder = new OnlineOrder(LocalDateTime.now(),shippingAddress);
        onlineOrder.setUser(user);
        onlineOrder.setStatus(OnlineOrderStatus.PENDING);
        return onlineOrderRepository.save(onlineOrder);
    }

    @Transactional
    public OnlineOrder addTotalAmountToOnlineOrder(Long onlineOrderId, BigDecimal totalAmount){
        OnlineOrder onlineOrder = onlineOrderRepository.findById(onlineOrderId)
                .orElseThrow(()-> new NotFoundException("OnlineOrder ID does not exist"));
        onlineOrder.setTotalAmount(totalAmount);
        return onlineOrderRepository.save(onlineOrder);
    }
}
