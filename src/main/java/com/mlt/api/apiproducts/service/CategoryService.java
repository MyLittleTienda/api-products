package com.mlt.api.apiproducts.service;

import com.mlt.api.apiproducts.domain.dto.data.CategoryDTO;
import com.mlt.api.apiproducts.domain.dto.request.body.CreateCategoryRequest;
import com.mlt.api.apiproducts.domain.dto.request.body.UpdateCategoryRequest;
import com.mlt.api.apiproducts.domain.dto.response.GetCategoriesData;

public interface CategoryService {

    GetCategoriesData getCategories();

    CategoryDTO createCategory(CreateCategoryRequest category);

    CategoryDTO updateCategory(UpdateCategoryRequest category, Integer id);

    CategoryDTO deleteCategory(Integer id);

}
