package com.stockstore.stockstore.online.mapper;

import com.stockstore.stockstore.online.dto.cartItem.CartItemListDTO;
import com.stockstore.stockstore.online.dto.cartItem.CartItemRequestDTO;
import com.stockstore.stockstore.online.model.CartItem;
import com.stockstore.stockstore.shared.mapper.ProductMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

@Mapper(componentModel = "spring")
public abstract class CartItemMapper {
    @Autowired
    @Lazy
    private CartMapper cartMapper;

    @Autowired
    @Lazy
    private ProductMapper productMapper;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "cart", ignore = true)
    public abstract CartItem toEntity(CartItemRequestDTO dto);
    public abstract CartItemListDTO toListDto(CartItem entity);
}
