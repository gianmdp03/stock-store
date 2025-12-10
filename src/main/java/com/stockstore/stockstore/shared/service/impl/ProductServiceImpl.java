package com.stockstore.stockstore.shared.service.impl;

import com.stockstore.stockstore.shared.dto.product.ProductDetailDTO;
import com.stockstore.stockstore.shared.dto.product.ProductListDTO;
import com.stockstore.stockstore.shared.dto.product.ProductRequestDTO;
import com.stockstore.stockstore.shared.mapper.ProductMapper;
import com.stockstore.stockstore.shared.repository.ProductRepository;
import com.stockstore.stockstore.shared.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    @Transactional
    public ProductDetailDTO addProduct(ProductRequestDTO dto) {
        return null;
    }

    @Override
    public Page<ProductListDTO> listProducts(Pageable pageable) {
        return null;
    }

    @Override
    @Transactional
    public void deleteProduct(Long productId) {

    }
}
