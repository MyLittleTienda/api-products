package com.mlt.api.apiproducts.mapper;

import com.mlt.api.apiproducts.domain.dto.data.CategoryDTO;
import com.mlt.api.apiproducts.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDTO toCategoryDTO(String category);

    CategoryDTO toCategoryDTO(Category category);

    Category toCategory(CategoryDTO categoryDTO);

    Category toCategory(String category);

}
