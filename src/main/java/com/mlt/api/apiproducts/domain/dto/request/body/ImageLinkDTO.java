package com.mlt.api.apiproducts.domain.dto.request.body;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mlt.api.common.domain.request.MltRequest;
import jakarta.validation.constraints.NotEmpty;
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
public class ImageLinkDTO extends MltRequest {

    @NotEmpty(message = "Image link cannot be empty")
    private String link;
    @NotEmpty(message = "Image link cannot be empty")
    private String provider;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

}
