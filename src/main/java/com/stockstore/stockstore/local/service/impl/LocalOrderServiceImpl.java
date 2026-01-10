package com.stockstore.stockstore.local.service.impl;

import com.stockstore.stockstore.local.mapper.LocalOrderItemMapper;
import com.stockstore.stockstore.local.mapper.LocalOrderMapper;
import com.stockstore.stockstore.local.repository.LocalOrderItemRepository;
import com.stockstore.stockstore.local.repository.LocalOrderRepository;
import com.stockstore.stockstore.shared.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LocalOrderServiceImpl {
    private final LocalOrderRepository localOrderRepository;
    private final LocalOrderItemRepository localOrderItemRepository;
    private final LocalOrderMapper localOrderMapper;
    private final LocalOrderItemMapper localOrderItemMapper;
    private final ProductRepository productRepository;

    
}
