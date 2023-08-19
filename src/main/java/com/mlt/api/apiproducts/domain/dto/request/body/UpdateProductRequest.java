package com.mlt.api.apiproducts.domain.dto.request.body;

import com.mlt.api.common.domain.request.MltRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateProductRequest extends MltRequest {

    private Long id;
    private String name;
    private String description;
    private List<Long> categories;
    private List<Double> prices;
    @Valid
    private List<ImageLinkDTO> images;

}
