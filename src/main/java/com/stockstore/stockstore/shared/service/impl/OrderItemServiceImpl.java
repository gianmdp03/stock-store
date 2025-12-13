package com.stockstore.stockstore.shared.service.impl;

import com.stockstore.stockstore.exception.NotFoundException;
import com.stockstore.stockstore.shared.dto.orderItem.OrderItemDetailDTO;
import com.stockstore.stockstore.shared.dto.orderItem.OrderItemRequestDTO;
import com.stockstore.stockstore.shared.dto.orderItem.OrderItemUpdateDTO;
import com.stockstore.stockstore.shared.mapper.OrderItemMapper;
import com.stockstore.stockstore.shared.model.Order;
import com.stockstore.stockstore.shared.model.OrderItem;
import com.stockstore.stockstore.shared.model.Product;
import com.stockstore.stockstore.shared.repository.OrderItemRepository;
import com.stockstore.stockstore.shared.repository.OrderRepository;
import com.stockstore.stockstore.shared.repository.ProductRepository;
import com.stockstore.stockstore.shared.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public OrderItemDetailDTO addOrderItem(OrderItemRequestDTO dto) {
        Product product = productRepository.findById(dto.productId()).orElseThrow(()->new NotFoundException("Product ID does not exist"));
        Order order = orderRepository.findById(dto.orderId()).orElseThrow(()->new NotFoundException("Order ID does not exist"));
        OrderItem orderItem = orderItemMapper.toEntity(dto);
        orderItem.setProduct(product);
        orderItem.setOrder(order);
        orderItem = orderItemRepository.save(orderItem);
        return orderItemMapper.toDetailDto(orderItem);
    }

    @Override
    @Transactional
    public OrderItemDetailDTO updateOrderItem(Long id, OrderItemUpdateDTO dto){
        OrderItem orderItem = orderItemRepository.findById(id).orElseThrow(()->new NotFoundException("Order Item ID does not exist"));
        orderItemMapper.updateEntityFromDto(dto, orderItem);
        orderItem = orderItemRepository.save(orderItem);
        return orderItemMapper.toDetailDto(orderItem);
    }

    @Override
    public Page<OrderItemDetailDTO> listOrderItems(Pageable pageable) {
        Page<OrderItem> page = orderItemRepository.findAll(pageable);
        if(page.isEmpty())
            throw new NotFoundException("OrderItem list is empty");
        return page.map(orderItemMapper::toDetailDto);
    }

    @Override
    @Transactional
    public void deleteOrderItem(Long orderItemId) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId).orElseThrow(()-> new NotFoundException("OrderItem ID does not exist"));
        orderItemRepository.delete(orderItem);
    }
}
