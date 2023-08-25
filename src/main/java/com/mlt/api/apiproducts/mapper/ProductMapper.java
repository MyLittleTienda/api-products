package com.mlt.api.apiproducts.mapper;

import com.mlt.api.apiproducts.domain.dto.data.ProductDTO;
import com.mlt.api.apiproducts.domain.dto.request.body.CreateProductRequest;
import com.mlt.api.apiproducts.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        PriceMapper.class, CategoryMapper.class, ProductCategoryMapper.class, ProductImageMapper.class
})
public interface ProductMapper {

    @Mapping(target = "categories", source = "productCategories")
    @Mapping(target = "images", source = "images")
    ProductDTO toProductDTO(Product product);

    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "productCategories", ignore = true)
    @Mapping(target = "images", source = "images")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    Product toProduct(CreateProductRequest productDTO);


}
