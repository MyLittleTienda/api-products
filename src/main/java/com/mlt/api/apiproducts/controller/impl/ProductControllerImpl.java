package com.mlt.api.apiproducts.controller.impl;

import com.mlt.api.apiproducts.controller.ProductController;
import com.mlt.api.apiproducts.domain.dto.data.ProductDTO;
import com.mlt.api.apiproducts.domain.dto.request.body.CreateProductRequest;
import com.mlt.api.apiproducts.domain.dto.data.ImageLinkDTO;
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
    public MltResponse<ProductDTO> getProductById(Integer id) {
        return productService.getProductById(id);
    }

    @Override
    public MltResponse<ProductDTO> createProduct(CreateProductRequest request) {
        return productService.createProduct(request);
    }

    @Override
    public MltResponse<ProductDTO> updateProductData(Integer id, UpdateProductRequest request) {
        return productService.updateProduct(id, request);
    }

    @Override
    public MltResponse<ProductDTO> deleteProduct(Integer id) {
        return productService.deleteProduct(id);
    }

    @Override
    public MltResponse<ProductDTO> addPrice(Integer id, Double price) {
        return productService.addPrice(id, price);
    }

    @Override
    public MltResponse<ProductDTO> addCategory(Integer id, Integer idCategory) {
        return productService.addCategory(id, idCategory);
    }

    @Override
    public MltResponse<ProductDTO> addImage(Integer id, ImageLinkDTO image) {
        return productService.addImage(id, image);
    }

    @Override
    public MltResponse<ProductDTO> removePrice(Integer id, Integer idPrice) {
        return productService.removePrice(id, idPrice);
    }

    @Override
    public MltResponse<ProductDTO> removeCategory(Integer id, Integer idCategory) {
        return productService.removeCategory(id, idCategory);
    }

    @Override
    public MltResponse<ProductDTO> removeImage(Integer id, Integer idImage) {
        return productService.removeImage(id, idImage);
    }
}