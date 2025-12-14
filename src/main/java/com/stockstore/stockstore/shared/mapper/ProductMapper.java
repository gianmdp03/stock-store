package com.stockstore.stockstore.shared.mapper;

import com.stockstore.stockstore.inventory.mapper.SupplierMapper;
import com.stockstore.stockstore.shared.dto.product.ProductDetailDTO;
import com.stockstore.stockstore.shared.dto.product.ProductListDTO;
import com.stockstore.stockstore.shared.dto.product.ProductRequestDTO;
import com.stockstore.stockstore.shared.dto.product.ProductUpdateDTO;
import com.stockstore.stockstore.shared.model.Product;
import lombok.RequiredArgsConstructor;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {
    @Autowired
    @Lazy
    private InventoryItemMapper inventoryItemMapper;

    @Autowired
    @Lazy
    private OrderMapper orderMapper;

    @Autowired
    @Lazy
    private SupplierMapper supplierMapper;

    @Autowired
    @Lazy
    private CategoryMapper categoryMapper;

    @Autowired
    @Lazy
    private OrderItemMapper orderItemMapper;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "inventoryItems", ignore = true)
    @Mapping(target = "orderItems", ignore = true)
    @Mapping(target = "supplier", ignore = true)
    @Mapping(target = "categories", ignore = true)
    public abstract Product toEntity(ProductRequestDTO dto);
    public abstract ProductDetailDTO toDetailDto(Product entity);
    public abstract ProductListDTO toListDto(Product entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "inventoryItems", ignore = true)
    @Mapping(target = "orderItems", ignore = true)
    @Mapping(target = "supplier", ignore = true)
    @Mapping(target = "categories", ignore = true)
    public abstract void updateEntityFromDto(ProductUpdateDTO dto, @MappingTarget Product product);
}
