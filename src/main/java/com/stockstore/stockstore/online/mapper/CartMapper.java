package com.stockstore.stockstore.online.mapper;

import com.stockstore.stockstore.online.dto.cart.CartListDTO;
import com.stockstore.stockstore.online.model.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CartItemMapper.class})
public abstract class CartMapper {
    public abstract CartListDTO toListDto(Cart entity);
}
