package com.stockstore.stockstore.shared.service.impl;

import com.stockstore.stockstore.exception.NotFoundException;
import com.stockstore.stockstore.shared.dto.order.OrderDetailDTO;
import com.stockstore.stockstore.shared.dto.order.OrderUpdateDTO;
import com.stockstore.stockstore.shared.mapper.OrderMapper;
import com.stockstore.stockstore.shared.model.Order;
import com.stockstore.stockstore.shared.repository.OrderRepository;
import com.stockstore.stockstore.shared.repository.ProductRepository;
import com.stockstore.stockstore.shared.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public OrderDetailDTO addOrder() {
        Order order = new Order(LocalDate.now());
        order = orderRepository.save(order);
        return orderMapper.toDetailDto(order);
    }

    @Override
    @Transactional
    public OrderDetailDTO updateOrder(Long id, OrderUpdateDTO dto){
        Order order = orderRepository.findById(id).orElseThrow(()->new NotFoundException("Order ID does not exist"));
        orderMapper.updateEntityFromDto(dto, order);
        order = orderRepository.save(order);
        return orderMapper.toDetailDto(order);
    }

    @Override
    public Page<OrderDetailDTO> listOrders(Pageable pageable) {
        Page<Order> page = orderRepository.findAll(pageable);
        if(page.isEmpty())
            throw new NotFoundException("Order list is empty");
        return page.map(orderMapper::toDetailDto);
    }

    @Override
    @Transactional
    public void deleteOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(()-> new NotFoundException("Order ID does not exist"));
        orderRepository.delete(order);
    }
}
