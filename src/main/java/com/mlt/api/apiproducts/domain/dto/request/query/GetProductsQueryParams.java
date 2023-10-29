package com.mlt.api.apiproducts.domain.dto.request.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mlt.api.common.domain.constantes.DateFormatConstants;
import com.mlt.api.common.domain.request.PageableMltRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetProductsQueryParams extends PageableMltRequest {

    private List<Long> ids;
    private String name;
    private String description;
    private List<String> categoryNames;
    private Double minPrice;
    private Double maxPrice;
    @JsonFormat(pattern = DateFormatConstants.DATE_FORMAT)
    private LocalDateTime createdAfter;
    @JsonFormat(pattern = DateFormatConstants.DATE_FORMAT)
    private LocalDateTime createdBefore;
    @JsonFormat(pattern = DateFormatConstants.DATE_FORMAT)
    private LocalDateTime updatedAfter;
    @JsonFormat(pattern = DateFormatConstants.DATE_FORMAT)
    private LocalDateTime updatedBefore;

}
