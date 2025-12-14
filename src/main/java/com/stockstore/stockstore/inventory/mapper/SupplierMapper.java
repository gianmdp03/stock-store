package com.stockstore.stockstore.inventory.mapper;

import com.stockstore.stockstore.inventory.dto.supplier.SupplierDetailDTO;
import com.stockstore.stockstore.inventory.dto.supplier.SupplierListDTO;
import com.stockstore.stockstore.inventory.dto.supplier.SupplierRequestDTO;
import com.stockstore.stockstore.inventory.dto.supplier.SupplierUpdateDTO;
import com.stockstore.stockstore.inventory.model.Supplier;
import com.stockstore.stockstore.shared.mapper.ProductMapper;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public abstract class SupplierMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "products", ignore = true)
    public abstract Supplier toEntity(SupplierRequestDTO dto);
    public abstract SupplierDetailDTO toDetailDto(Supplier entity);
    public abstract SupplierListDTO toListDto(Supplier entity);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "products", ignore = true)
    public abstract void updateEntityFromDto(SupplierUpdateDTO dto, @MappingTarget Supplier entity);

}
