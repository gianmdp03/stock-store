package com.stockstore.stockstore.shared.mapper;

import com.stockstore.stockstore.shared.dto.category.CategoryDetailDTO;
import com.stockstore.stockstore.shared.dto.category.CategoryListDTO;
import com.stockstore.stockstore.shared.dto.category.CategoryRequestDTO;
import com.stockstore.stockstore.shared.dto.category.CategoryUpdateDTO;
import com.stockstore.stockstore.shared.model.Category;
import org.mapstruct.*;

@Mapper(componentModel = "spring",  uses = {ProductMapper.class})
public abstract class CategoryMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "products", ignore = true)
    public abstract Category toEntity(CategoryRequestDTO dto);
    public abstract CategoryDetailDTO toDetailDto(Category entity);
    public abstract CategoryListDTO toListDto(Category entity);
    
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "products", ignore = true)
    public abstract void updateEntityFromDto(CategoryUpdateDTO dto, @MappingTarget Category entity);
}
