package com.mlt.api.apiproducts.controller.impl;

import com.mlt.api.apiproducts.controller.CategoryController;
import com.mlt.api.apiproducts.domain.dto.data.CategoryDTO;
import com.mlt.api.apiproducts.domain.dto.request.body.CreateCategoryRequest;
import com.mlt.api.apiproducts.domain.dto.request.body.UpdateCategoryRequest;
import com.mlt.api.apiproducts.domain.dto.response.GetCategoriesData;
import com.mlt.api.apiproducts.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoryControllerImpl implements CategoryController {

    private final CategoryService categoryService;

    @Override
    public GetCategoriesData getCategories() {
        return categoryService.getCategories();
    }

    @Override
    public CategoryDTO createCategory(CreateCategoryRequest category) {
        return categoryService.createCategory(category);
    }

    @Override
    public CategoryDTO updateCategory(UpdateCategoryRequest category, Integer id) {
        return categoryService.updateCategory(category, id);
    }

    @Override
    public CategoryDTO deleteCategory(Integer id) {
        return categoryService.deleteCategory(id);
    }
}
