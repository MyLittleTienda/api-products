package com.mlt.api.apiproducts.mapper;

import com.mlt.api.apiproducts.domain.dto.data.ProductDTO;
import com.mlt.api.apiproducts.domain.dto.request.body.CreateProductRequest;
import com.mlt.api.apiproducts.model.Product;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {PriceMapper.class, CategoryMapper.class, ProductCategoryMapper.class, ProductImageMapper.class})
public abstract class ProductMapper {

    @Mapping(target = "categories", source = "productCategories")
    @Mapping(target = "images", source = "images")
    public abstract ProductDTO toProductDTO(Product product);

    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "productCategories", ignore = true)
    @Mapping(target = "images", source = "images")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    public abstract Product toProduct(CreateProductRequest productDTO);

    @AfterMapping
    public Product mapProductInPrices(@MappingTarget Product.ProductBuilder product) {
        Product result = product.build();
        result.getPrices().forEach(p -> p.setProduct(result));
        return result;
    }


}
