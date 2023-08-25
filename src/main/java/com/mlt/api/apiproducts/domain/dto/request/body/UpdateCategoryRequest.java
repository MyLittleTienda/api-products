package com.mlt.api.apiproducts.domain.dto.request.body;

import com.mlt.api.common.domain.request.MltRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class UpdateCategoryRequest extends MltRequest {

    @NotNull(message = "id is required")
    private Integer id;
    @NotBlank(message = "name is required")
    private String name;

}
