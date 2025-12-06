package com.stockstore.stockstore.shared.service;

import com.stockstore.stockstore.shared.dto.product.ProductDetailDTO;
import com.stockstore.stockstore.shared.dto.product.ProductListDTO;
import com.stockstore.stockstore.shared.dto.product.ProductRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductDetailDTO addProduct(ProductRequestDTO dto);
    Page<ProductListDTO> listProducts(Pageable pageable);
    void deleteProduct(Long productId);
}
