package com.mlt.api.apiproducts.controller;

import com.mlt.api.apiproducts.domain.dto.request.body.CreateProductRequest;
import com.mlt.api.apiproducts.domain.dto.request.body.UpdateProductRequest;
import com.mlt.api.apiproducts.domain.dto.request.query.GetProductsQueryParams;
import com.mlt.api.apiproducts.domain.dto.response.GetProductsData;
import com.mlt.api.common.domain.response.MltResponse;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/products")
@Validated
public interface ProductController {

    @GetMapping
    MltResponse<GetProductsData> getProducts(@Valid GetProductsQueryParams queryParams);

    @GetMapping("/{id}")
    MltResponse getProductById(@PathVariable Long id);

    @PostMapping
    MltResponse createProduct(@RequestBody @Valid CreateProductRequest request);

    @PutMapping("/{id}")
    MltResponse updateProduct(@PathVariable Long id, UpdateProductRequest request);

    @DeleteMapping("/{id}")
    MltResponse deleteProduct(@PathVariable Long id);

}
