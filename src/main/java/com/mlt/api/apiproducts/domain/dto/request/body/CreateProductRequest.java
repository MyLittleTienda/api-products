package com.mlt.api.apiproducts.domain.dto.request.body;

import com.mlt.api.common.domain.request.MltRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
public class CreateProductRequest extends MltRequest {

    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotEmpty
    private List<Long> categories;
    @NotEmpty
    private List<Double> prices;
    @Valid
    private List<ImageLinkDTO> images;

}
