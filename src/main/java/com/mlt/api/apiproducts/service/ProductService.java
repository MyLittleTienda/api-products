package com.mlt.api.apiproducts.service;

import com.mlt.api.apiproducts.domain.dto.data.ProductDTO;
import com.mlt.api.apiproducts.domain.dto.request.body.CreateProductRequest;
import com.mlt.api.apiproducts.domain.dto.request.body.UpdateProductRequest;
import com.mlt.api.apiproducts.domain.dto.request.query.GetProductsQueryParams;
import com.mlt.api.apiproducts.domain.dto.response.GetProductsData;
import com.mlt.api.common.domain.response.MltResponse;

public interface ProductService {

    MltResponse<GetProductsData> getProducts(GetProductsQueryParams queryParams);

    MltResponse<ProductDTO> getProductById(Long id);

    MltResponse<ProductDTO> createProduct(CreateProductRequest productDTO);

    MltResponse<ProductDTO> updateProduct(Long id, UpdateProductRequest productDTO);

    MltResponse<ProductDTO> deleteProduct(Long id);

}
