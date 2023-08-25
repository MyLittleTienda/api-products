package com.mlt.api.apiproducts.domain.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mlt.api.common.domain.response.MltData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDTO extends MltData {

    private Integer id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String name;

}
