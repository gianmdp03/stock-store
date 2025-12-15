package com.stockstore.stockstore.shared.service;

import com.stockstore.stockstore.shared.dto.product.ProductDetailDTO;
import com.stockstore.stockstore.shared.dto.product.ProductListDTO;
import com.stockstore.stockstore.shared.dto.product.ProductRequestDTO;
import com.stockstore.stockstore.shared.dto.product.ProductUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductDetailDTO addProduct(ProductRequestDTO dto);
    Page<ProductListDTO> listProducts(Pageable pageable);
    ProductDetailDTO updateProduct(Long id, ProductUpdateDTO dto);
    Page<ProductListDTO> searchProductsByName(String name, Pageable pageable);
    void deleteProduct(Long productId);
}
