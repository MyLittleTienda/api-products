package com.mlt.api.apiproducts.domain.dto.request.body;

import com.mlt.api.common.domain.request.MltRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateCategoryRequest extends MltRequest {

    @NotBlank(message = "name is required")
    private String name;

}
