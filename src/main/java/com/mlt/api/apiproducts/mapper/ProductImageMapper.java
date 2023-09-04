package com.mlt.api.apiproducts.mapper;

import com.mlt.api.apiproducts.domain.dto.data.ImageLinkDTO;
import com.mlt.api.apiproducts.model.ProductImage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductImageMapper {

    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "imageLink", source = "link")
    @Mapping(target = "provider", source = "provider")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    ProductImage toProductImage(ImageLinkDTO imageLinkDTO);

    @Mapping(target = "link", source = "imageLink")
    @Mapping(target = "id", source = "id")
    ImageLinkDTO toImageLinkDTO(ProductImage productImage);

}
