package com.stockstore.stockstore.online.mapper;

import com.stockstore.stockstore.online.dto.wishlist.WishlistDetailDTO;
import com.stockstore.stockstore.online.model.Wishlist;
import com.stockstore.stockstore.shared.mapper.ProductMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ProductMapper.class)
public abstract class WishlistMapper{
    public abstract WishlistDetailDTO toDetailDto(Wishlist entity);
}
