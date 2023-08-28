package com.mlt.api.apiproducts.controller;

import com.mlt.api.apiproducts.domain.dto.data.CategoryDTO;
import com.mlt.api.apiproducts.domain.dto.request.body.CreateCategoryRequest;
import com.mlt.api.apiproducts.domain.dto.request.body.UpdateCategoryRequest;
import com.mlt.api.apiproducts.domain.dto.response.GetCategoriesData;
import com.mlt.api.common.domain.response.MltResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categories")
public interface CategoryController {

    @GetMapping("")
    MltResponse<GetCategoriesData> getCategories();

    @PostMapping("")
    MltResponse<CategoryDTO> createCategory(@RequestBody @Valid CreateCategoryRequest category);

    @PutMapping("/{id}")
    MltResponse<CategoryDTO> updateCategory(@RequestBody @Valid UpdateCategoryRequest category, @PathVariable Integer id);

    @DeleteMapping("/{id}")
    MltResponse<CategoryDTO> deleteCategory(@PathVariable Integer id);

}
