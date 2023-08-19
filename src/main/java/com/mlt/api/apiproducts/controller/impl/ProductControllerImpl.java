package com.mlt.api.apiproducts.controller.impl;

import com.mlt.api.apiproducts.controller.ProductController;
import com.mlt.api.apiproducts.domain.dto.data.ProductDTO;
import com.mlt.api.apiproducts.domain.dto.request.body.CreateProductRequest;
import com.mlt.api.apiproducts.domain.dto.request.body.UpdateProductRequest;
import com.mlt.api.apiproducts.domain.dto.request.query.GetProductsQueryParams;
import com.mlt.api.apiproducts.domain.dto.response.GetProductsData;
import com.mlt.api.apiproducts.service.ProductService;
import com.mlt.api.common.domain.response.MltResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductControllerImpl implements ProductController {

    private final ProductService productService;

    @Override
    public MltResponse<GetProductsData> getProducts(GetProductsQueryParams queryParams) {
        return productService.getProducts(queryParams);
    }

    @Override
    public MltResponse<ProductDTO> getProductById(Long id) {
        return productService.getProductById(id);
    }

    @Override
    public MltResponse<ProductDTO> createProduct(CreateProductRequest request) {
        return productService.createProduct(request);
    }

    @Override
    public MltResponse<ProductDTO> updateProduct(Long id, UpdateProductRequest request) {
        return productService.updateProduct(id, request);
    }

    @Override
    public MltResponse<ProductDTO> deleteProduct(Long id) {
        return productService.deleteProduct(id);
    }
}