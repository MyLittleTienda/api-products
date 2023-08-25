package com.mlt.api.apiproducts.mapper;

import com.mlt.api.apiproducts.domain.dto.data.CategoryDTO;
import com.mlt.api.apiproducts.model.Category;
import com.mlt.api.apiproducts.model.Product;
import com.mlt.api.apiproducts.model.ProductCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductCategoryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", source = "category")
    @Mapping(target = "product", source = "product")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "deletedAt", ignore = true)
    ProductCategory toProductCategory(Category category, Product product);

    @Mapping(target = "id", source = "productCategory.category.id")
    @Mapping(target = "name", source = "productCategory.category.name")
    CategoryDTO toCategoryDTO(ProductCategory productCategory);

    List<CategoryDTO> toCategoryDTOList(List<ProductCategory> productCategoryList);

}
