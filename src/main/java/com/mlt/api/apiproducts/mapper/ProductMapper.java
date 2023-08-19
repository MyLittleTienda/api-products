package com.mlt.api.apiproducts.mapper;

import com.mlt.api.apiproducts.domain.dto.data.ProductDTO;
import com.mlt.api.apiproducts.domain.dto.request.body.CreateProductRequest;
import com.mlt.api.apiproducts.domain.dto.request.body.UpdateProductRequest;
import com.mlt.api.apiproducts.model.Product;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {
        PriceMapper.class, CategoryMapper.class, ProductCategoryMapper.class, ProductImageMapper.class
})
public interface ProductMapper {

    @Mapping(target = "categories", source = "productCategories")
    @Mapping(target = "images", source = "images")
    ProductDTO toProductDTO(Product product);


    @Mapping(target = "productCategories", ignore = true)
    @Mapping(target = "images", source = "images")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    Product toProduct(UpdateProductRequest productDTO);

    @AfterMapping
    default Product afterToProduct(@MappingTarget Product.ProductBuilder product) {
        Product productBuilt = product.build();
        productBuilt.getPrices().forEach(price -> price.setProduct(productBuilt));
        productBuilt.getImages().forEach(image -> image.setProduct(productBuilt));
        return productBuilt;
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "productCategories", ignore = true)
    @Mapping(target = "images", source = "images")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    Product toProduct(CreateProductRequest productDTO);


}
