package com.mlt.api.apiproducts.service;

import com.mlt.api.apiproducts.domain.dto.data.CategoryDTO;
import com.mlt.api.apiproducts.domain.dto.request.body.CreateCategoryRequest;
import com.mlt.api.apiproducts.domain.dto.request.body.UpdateCategoryRequest;
import com.mlt.api.apiproducts.domain.dto.response.GetCategoriesData;
import com.mlt.api.common.domain.response.MltResponse;

public interface CategoryService {
  MltResponse<GetCategoriesData> getCategories();

  MltResponse<CategoryDTO> createCategory(CreateCategoryRequest category);

  MltResponse<CategoryDTO> updateCategory(
    UpdateCategoryRequest category,
    Integer id
  );

  MltResponse<CategoryDTO> deleteCategory(Integer id);
}
