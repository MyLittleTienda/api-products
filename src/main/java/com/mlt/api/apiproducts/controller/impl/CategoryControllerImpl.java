package com.mlt.api.apiproducts.controller.impl;

import com.mlt.api.apiproducts.controller.CategoryController;
import com.mlt.api.apiproducts.domain.dto.data.CategoryDTO;
import com.mlt.api.apiproducts.domain.dto.request.body.CreateCategoryRequest;
import com.mlt.api.apiproducts.domain.dto.request.body.UpdateCategoryRequest;
import com.mlt.api.apiproducts.domain.dto.response.GetCategoriesData;
import com.mlt.api.apiproducts.service.CategoryService;
import com.mlt.api.common.domain.response.MltResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoryControllerImpl implements CategoryController {

    private final CategoryService categoryService;

    @Override
    public MltResponse<GetCategoriesData> getCategories() {
        return categoryService.getCategories();
    }

    @Override
    public MltResponse<CategoryDTO> createCategory(CreateCategoryRequest category) {
        return categoryService.createCategory(category);
    }

    @Override
    public MltResponse<CategoryDTO> updateCategory(UpdateCategoryRequest category, Integer id) {
        return categoryService.updateCategory(category, id);
    }

    @Override
    public MltResponse<CategoryDTO> deleteCategory(Integer id) {
        return categoryService.deleteCategory(id);
    }

}
