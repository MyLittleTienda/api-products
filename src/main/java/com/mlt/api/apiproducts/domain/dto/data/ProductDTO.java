package com.mlt.api.apiproducts.domain.dto.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mlt.api.apiproducts.domain.dto.request.body.ImageLinkDTO;
import com.mlt.api.common.domain.constantes.DateFormatConstants;
import com.mlt.api.common.domain.response.MltData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductDTO extends MltData {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private String name;
    private String description;
    @JsonFormat(pattern = DateFormatConstants.DATE_TIME_FORMAT)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;
    @JsonFormat(pattern = DateFormatConstants.DATE_TIME_FORMAT)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updatedAt;
    private List<CategoryDTO> categories;
    private List<PriceDTO> prices;
    private List<ImageLinkDTO> images;

}
