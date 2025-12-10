package com.stockstore.stockstore.inventory.mapper;

import com.stockstore.stockstore.inventory.dto.supplier.SupplierDetailDTO;
import com.stockstore.stockstore.inventory.dto.supplier.SupplierListDTO;
import com.stockstore.stockstore.inventory.dto.supplier.SupplierRequestDTO;
import com.stockstore.stockstore.inventory.model.Supplier;
import com.stockstore.stockstore.shared.mapper.ProductMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface SupplierMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "products", ignore = true)
    Supplier toEntity(SupplierRequestDTO dto);
    SupplierDetailDTO toDetailDto(Supplier entity);
    SupplierListDTO toListDto(Supplier entity);
}
