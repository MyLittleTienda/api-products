package com.mlt.api.apiproducts.controller;

import com.mlt.api.apiproducts.domain.dto.data.CategoryDTO;
import com.mlt.api.apiproducts.domain.dto.request.body.CreateCategoryRequest;
import com.mlt.api.apiproducts.domain.dto.request.body.UpdateCategoryRequest;
import com.mlt.api.apiproducts.domain.dto.response.GetCategoriesData;
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
    GetCategoriesData getCategories();

    @PostMapping("")
    CategoryDTO createCategory(@RequestBody @Valid CreateCategoryRequest category);

    @PutMapping("/{id}")
    CategoryDTO updateCategory(@RequestBody @Valid UpdateCategoryRequest category, @PathVariable Integer id);

    @DeleteMapping("/{id}")
    CategoryDTO deleteCategory(@PathVariable Integer id);

}
