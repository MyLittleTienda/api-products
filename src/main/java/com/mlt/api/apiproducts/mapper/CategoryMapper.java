package com.mlt.api.apiproducts.mapper;

import com.mlt.api.apiproducts.domain.dto.data.CategoryDTO;
import com.mlt.api.apiproducts.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {


    @Mapping(target = "id", source = "category.id")
    @Mapping(target = "name", source = "category.name")
    CategoryDTO toCategoryDTO(Category category);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "name", source = "category")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    Category toCategory(String category);

}
