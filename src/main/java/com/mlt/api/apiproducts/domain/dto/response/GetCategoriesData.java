package com.mlt.api.apiproducts.domain.dto.response;

import com.mlt.api.apiproducts.domain.dto.data.CategoryDTO;
import com.mlt.api.common.domain.response.MltData;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public class GetCategoriesData extends MltData {

    private List<CategoryDTO> categories;
}
