package com.mlt.api.apiproducts.mapper;

import com.mlt.api.apiproducts.domain.dto.data.PriceDTO;
import com.mlt.api.apiproducts.model.Price;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PriceMapper {


    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(source = "value", target = "price")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    Price toPrice(Double value);

    @Mapping(target = "value", expression = "java(java.math.BigDecimal.valueOf(price.getPrice()))")
    PriceDTO toPriceDTO(Price price);

    @BeanMapping(resultType = ArrayList.class)
    List<Price> doubleListToPriceList(List<Double> prices);

}
