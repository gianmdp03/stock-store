package com.stockstore.stockstore.shared.service.impl;

import com.stockstore.stockstore.exception.NotFoundException;
import com.stockstore.stockstore.security.user.model.User;
import com.stockstore.stockstore.security.user.repository.UserRepository;
import com.stockstore.stockstore.shared.dto.order.OrderDetailDTO;
import com.stockstore.stockstore.shared.enums.OrderStatus;
import com.stockstore.stockstore.shared.mapper.OrderMapper;
import com.stockstore.stockstore.shared.model.Order;
import com.stockstore.stockstore.shared.repository.OrderRepository;
import com.stockstore.stockstore.shared.service.OrderService;
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

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public OrderDetailDTO addOrder(String shippingAddress) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));

        BigDecimal total = BigDecimal.ZERO;
        Order order = new Order(LocalDateTime.now(),shippingAddress);
        order.setUser(user);
        order.setStatus(OrderStatus.PENDING);
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
    public Page<OrderDetailDTO> searchOrders(LocalDate saleDate, Pageable pageable){
        LocalDateTime fullSaleDate = saleDate.atStartOfDay();
        Page<Order> page = orderRepository.findAllBySaleDate(fullSaleDate, pageable);
        return page.map(orderMapper::toDetailDto);
    }

    @Override
    public Page<OrderDetailDTO> searchOrdersBetween(LocalDate start, LocalDate end, Pageable pageable) {
        LocalDateTime startDate = start.atStartOfDay();
        LocalDateTime endDate = end.atTime(LocalTime.MAX);
        Page<Order> page = orderRepository.findBySaleDateBetween(startDate, endDate, pageable);
        if(page.isEmpty()){
            throw new NotFoundException("List is empty");
        }
        return page.map(orderMapper::toDetailDto);
    }
}
