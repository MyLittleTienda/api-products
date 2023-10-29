package com.mlt.api.apiproducts.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mlt.api.apiproducts.domain.dto.data.CategoryDTO;
import com.mlt.api.apiproducts.domain.dto.request.body.CreateCategoryRequest;
import com.mlt.api.apiproducts.domain.dto.request.body.UpdateCategoryRequest;
import com.mlt.api.apiproducts.domain.dto.response.GetCategoriesData;
import com.mlt.api.apiproducts.mapper.CategoryMapper;
import com.mlt.api.apiproducts.model.Category;
import com.mlt.api.apiproducts.repository.CategoryRepository;
import com.mlt.api.apiproducts.service.CategoryService;
import com.mlt.api.common.domain.response.MltResponse;
import com.mlt.api.common.handler.error.exception.notfound.CategoryNotFoundException;
import com.mlt.api.common.handler.error.exception.validation.CategoryExistsException;
import com.mlt.api.common.handler.error.exception.validation.IdsNotMatchException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public MltResponse<GetCategoriesData> getCategories() {
        List<Category> categories = categoryRepository.findAll(CategoryRepository.categoryDeletedAtNotNull());
        return MltResponse.<GetCategoriesData>builder().data(GetCategoriesData.builder()
                .categories(categories.stream()
                        .map(categoryMapper::toCategoryDTO)
                        .toList())
                .build()).build();
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public MltResponse<CategoryDTO> createCategory(CreateCategoryRequest category) {
        if (!categoryRepository.findAll(CategoryRepository.categoryNameEquals(category.getName())).isEmpty()) {
            throw CategoryExistsException.builder().name(category.getName()).build();
        }
        return MltResponse.<CategoryDTO>builder()
                .data(categoryMapper
                        .toCategoryDTO(categoryRepository.save(categoryMapper.toCategory(category.getName()))))
                .build();
    }

    @Override
    public MltResponse<CategoryDTO> updateCategory(UpdateCategoryRequest categoryRequest, Integer id) {
        if (!id.equals(categoryRequest.getId())) {
            throw IdsNotMatchException.builder(List.of(id.toString())).build();
        }

        if (!categoryRepository.findAll(CategoryRepository.categoryNameEquals(categoryRequest.getName())).isEmpty()) {
            throw CategoryExistsException.builder().name(categoryRequest.getName()).build();
        }

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> CategoryNotFoundException.builder(id.toString())
                        .build());
        category.setName(categoryRequest.getName());

        return MltResponse.<CategoryDTO>builder()
                .data(categoryMapper.toCategoryDTO(categoryRepository.save(category)))
                .build();
    }

    @Override
    public MltResponse<CategoryDTO> deleteCategory(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> CategoryNotFoundException.builder(id.toString())
                        .build());

        category.setDeletedAt(LocalDateTime.now());
        categoryRepository.save(category);

        return MltResponse.<CategoryDTO>builder().data(categoryMapper.toCategoryDTO(category)).build();
    }
}
