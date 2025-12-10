package com.stockstore.stockstore.shared.mapper;

import com.stockstore.stockstore.inventory.mapper.SupplierMapper;
import com.stockstore.stockstore.shared.dto.product.ProductDetailDTO;
import com.stockstore.stockstore.shared.dto.product.ProductListDTO;
import com.stockstore.stockstore.shared.dto.product.ProductRequestDTO;
import com.stockstore.stockstore.shared.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {InventoryItemMapper.class, OrderMapper.class, SupplierMapper.class, CategoryMapper.class})
public interface ProductMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "inventoryItems", ignore = true)
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "supplier", ignore = true)
    @Mapping(target = "categories", ignore = true)
    Product toEntity(ProductRequestDTO dto);
    ProductDetailDTO toDetailDto(Product entity);
    ProductListDTO toListDto(Product entity);
}
