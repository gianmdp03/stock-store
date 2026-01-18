package com.stockstore.stockstore.online.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class AddressMapper {
    @Mapping(target = "id", ignore = true)

}
