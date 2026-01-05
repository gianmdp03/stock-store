package com.stockstore.stockstore.online.service.impl;

import com.stockstore.stockstore.online.mapper.CartItemMapper;
import com.stockstore.stockstore.online.mapper.CartMapper;
import com.stockstore.stockstore.online.repository.CartItemRepository;
import com.stockstore.stockstore.online.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartServiceImpl {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final CartMapper cartMapper;
    private final CartItemMapper cartItemMapper;
}
