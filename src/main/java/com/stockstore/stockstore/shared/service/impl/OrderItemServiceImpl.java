package com.stockstore.stockstore.shared.service.impl;

import com.stockstore.stockstore.exception.NotFoundException;
import com.stockstore.stockstore.shared.dto.order.OrderDetailDTO;
import com.stockstore.stockstore.shared.dto.order.OrderListDTO;
import com.stockstore.stockstore.shared.dto.order.OrderRequestDTO;
import com.stockstore.stockstore.shared.dto.orderItem.OrderItemDetailDTO;
import com.stockstore.stockstore.shared.dto.orderItem.OrderItemListDTO;
import com.stockstore.stockstore.shared.mapper.OrderItemMapper;
import com.stockstore.stockstore.shared.mapper.OrderMapper;
import com.stockstore.stockstore.shared.model.Order;
import com.stockstore.stockstore.shared.model.OrderItem;
import com.stockstore.stockstore.shared.model.Product;
import com.stockstore.stockstore.shared.repository.OrderItemRepository;
import com.stockstore.stockstore.shared.repository.OrderRepository;
import com.stockstore.stockstore.shared.repository.ProductRepository;
import com.stockstore.stockstore.shared.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional(readOnly = true)
@NoArgsConstructor
public class OrderItemServiceImpl implements OrderService {
    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;

    @Override
    @Transactional
    public OrderItemDetailDTO addOrderItem(OrderRequestDTO dto) {
        OrderItem orderItem = orderItemMapper.toEntity(dto);
        return orderItemMapper.toDetailDto(orderItem);
    }

    @Override
    public Page<OrderItemListDTO> listOrderItems(Pageable pageable) {
        Page<OrderItem> page = orderItemRepository.findAll(pageable);
        if(page.isEmpty())
            throw new NotFoundException("OrderItem list is empty");
        return page.map(orderItemMapper::toListDto);
    }

    @Override
    @Transactional
    public void deleteOrder(Long orderItemId) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId).orElseThrow(()-> new NotFoundException("OrderItem ID does not exist"));
        orderItemRepository.delete(orderItem);
    }
}
