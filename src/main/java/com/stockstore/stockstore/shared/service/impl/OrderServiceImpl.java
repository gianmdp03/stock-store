package com.stockstore.stockstore.shared.service.impl;

import com.stockstore.stockstore.shared.dto.order.OrderDetailDTO;
import com.stockstore.stockstore.shared.dto.order.OrderListDTO;
import com.stockstore.stockstore.shared.dto.order.OrderRequestDTO;
import com.stockstore.stockstore.shared.mapper.OrderMapper;
import com.stockstore.stockstore.shared.repository.OrderRepository;
import com.stockstore.stockstore.shared.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    @Transactional
    public OrderDetailDTO addOrder(OrderRequestDTO dto) {
        return null;
    }

    @Override
    public Page<OrderListDTO> listOrders(Pageable pageable) {
        return null;
    }

    @Override
    @Transactional
    public void deleteOrder(Long orderId) {

    }
}
