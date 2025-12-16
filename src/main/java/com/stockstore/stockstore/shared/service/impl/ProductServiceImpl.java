package com.stockstore.stockstore.shared.service.impl;

import com.stockstore.stockstore.exception.NotFoundException;
import com.stockstore.stockstore.inventory.model.Supplier;
import com.stockstore.stockstore.inventory.repository.SupplierRepository;
import com.stockstore.stockstore.shared.dto.product.ProductDetailDTO;
import com.stockstore.stockstore.shared.dto.product.ProductListDTO;
import com.stockstore.stockstore.shared.dto.product.ProductRequestDTO;
import com.stockstore.stockstore.shared.dto.product.ProductUpdateDTO;
import com.stockstore.stockstore.shared.mapper.ProductMapper;
import com.stockstore.stockstore.shared.model.Category;
import com.stockstore.stockstore.shared.model.Product;
import com.stockstore.stockstore.shared.repository.CategoryRepository;
import com.stockstore.stockstore.shared.repository.ProductRepository;
import com.stockstore.stockstore.shared.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public ProductDetailDTO addProduct(ProductRequestDTO dto) {
        Optional<Product> optionalProduct = productRepository.findByName(dto.name());
        if(optionalProduct.isPresent()){
           Product existingProduct = optionalProduct.get();
           existingProduct.setEnabled(true);
           existingProduct = productRepository.save(existingProduct);
           return productMapper.toDetailDto(existingProduct);
        }
        List<Category> categories = categoryRepository.findAllById(dto.categoriesId());
        if(categories.isEmpty())
            throw new NotFoundException("List is empty");
        Product product = productMapper.toEntity(dto);
        product.setCategories(categories);
        product = productRepository.save(product);
        return productMapper.toDetailDto(product);
    }

    @Override
    @Transactional
    public ProductDetailDTO updateProduct(Long id, ProductUpdateDTO dto) {
        Product product = productRepository.findById(id).orElseThrow(()-> new NotFoundException("Product id does not exist"));
        productMapper.updateEntityFromDto(dto,product);
        product = productRepository.save(product);
        return productMapper.toDetailDto(product);
    }

    @Override
    public Page<ProductListDTO> listProducts(Pageable pageable) {
        Page<Product> page = productRepository.findByEnabledTrue(pageable);
        if (page.isEmpty())
            throw new NotFoundException("List is empty");
        return page.map(productMapper::toListDto);
    }

    @Override
    public Page<ProductListDTO> searchProductsByName(String name, Pageable pageable){
        if(name == null || name.isBlank()){
            return Page.empty();
        }
        Page<Product> page = productRepository.findByNameContainingIgnoreCaseAndEnabledTrue(name, pageable);
        return page.map(productMapper::toListDto);
    }

    @Override
    @Transactional
    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(()->new NotFoundException("Product ID does not exist"));
        product.setEnabled(false);
        productRepository.save(product);
    }
}
