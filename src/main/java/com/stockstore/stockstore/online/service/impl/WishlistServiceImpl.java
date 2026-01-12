package com.stockstore.stockstore.online.service.impl;

import com.stockstore.stockstore.online.repository.WishlistRepository;
import com.stockstore.stockstore.online.service.WishlistService;
import com.stockstore.stockstore.shared.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {
    private final WishlistRepository wishlistRepository;
    private final ProductRepository productRepository;
}
