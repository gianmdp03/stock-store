package com.stockstore.stockstore.online.mapper;

import com.stockstore.stockstore.online.dto.address.AddressDetailDTO;
import com.stockstore.stockstore.online.dto.address.AddressRequestDTO;
import com.stockstore.stockstore.online.dto.address.AddressUpdateDTO;
import com.stockstore.stockstore.online.model.Address;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class AddressMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    public abstract Address toEntity(AddressRequestDTO dto);
    public abstract AddressDetailDTO toDetailDto(Address entity);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    public abstract void updateEntityFromDto(AddressUpdateDTO dto, @MappingTarget Address entity);
}
