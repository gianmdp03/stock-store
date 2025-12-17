package com.stockstore.stockstore.security.user.mapper;

import com.stockstore.stockstore.security.user.dto.user.UserDetailDTO;
import com.stockstore.stockstore.security.user.dto.user.UserListDTO;
import com.stockstore.stockstore.security.user.dto.user.UserUpdateDTO;
import com.stockstore.stockstore.security.user.model.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    @Mapping(target = "id", ignore = true)
    public abstract User toEntity(UserDetailDTO dto);
    public abstract UserDetailDTO toDetailDto(User entity);
    public abstract UserListDTO toListDto(User entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    public abstract void updateEntityFromDto(UserUpdateDTO dto, @MappingTarget User entity);
}
