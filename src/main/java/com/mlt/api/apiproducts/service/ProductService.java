package com.mlt.api.apiproducts.service;

import com.mlt.api.apiproducts.domain.dto.data.ProductDTO;
import com.mlt.api.apiproducts.domain.dto.request.body.CreateProductRequest;
import com.mlt.api.apiproducts.domain.dto.request.body.ImageLinkDTO;
import com.mlt.api.apiproducts.domain.dto.request.body.UpdateProductRequest;
import com.mlt.api.apiproducts.domain.dto.request.query.GetProductsQueryParams;
import com.mlt.api.apiproducts.domain.dto.response.GetProductsData;
import com.mlt.api.common.domain.response.MltResponse;

public interface ProductService {

    MltResponse<GetProductsData> getProducts(GetProductsQueryParams queryParams);

    MltResponse<ProductDTO> getProductById(Integer id);

    MltResponse<ProductDTO> createProduct(CreateProductRequest productDTO);

    MltResponse<ProductDTO> updateProduct(Integer id, UpdateProductRequest productDTO);

    MltResponse<ProductDTO> deleteProduct(Integer id);

    MltResponse<ProductDTO> addPrice(Integer id, Double price);

    MltResponse<ProductDTO> addCategory(Integer id, Integer idCategory);

    MltResponse<ProductDTO> addImage(Integer id, ImageLinkDTO request);

    MltResponse<ProductDTO> removePrice(Integer id, Integer idPrice);

    MltResponse<ProductDTO> removeCategory(Integer id, Integer idCategory);

    MltResponse<ProductDTO> removeImage(Integer id, Integer idImage);

}
