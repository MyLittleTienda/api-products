package com.mlt.api.apiproducts.controller;

import com.mlt.api.apiproducts.domain.dto.data.ProductDTO;
import com.mlt.api.apiproducts.domain.dto.request.body.CreateProductRequest;
import com.mlt.api.apiproducts.domain.dto.request.body.ImageLinkDTO;
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
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/api/v1/products")
@Validated
public interface ProductController {

    @GetMapping
    MltResponse<GetProductsData> getProducts(@Valid GetProductsQueryParams queryParams);

    @GetMapping("/{id}")
    MltResponse<ProductDTO> getProductById(@PathVariable Integer id);

    @PostMapping
    MltResponse<ProductDTO> createProduct(@RequestBody @Valid CreateProductRequest request);

    @PutMapping("/{id}")
    MltResponse<ProductDTO> updateProductData(@PathVariable Integer id, @RequestBody @Valid UpdateProductRequest request);

    @DeleteMapping("/{id}")
    MltResponse<ProductDTO> deleteProduct(@PathVariable Integer id);

    @PutMapping("/{id}/prices")
    MltResponse<ProductDTO> addPrice(@PathVariable Integer id, @RequestParam Double price);

    @PutMapping("/{id}/categories")
    MltResponse<ProductDTO> addCategory(@PathVariable Integer id, @RequestParam Integer idCategory);

    @PutMapping("/{id}/images")
    MltResponse<ProductDTO> addImage(@PathVariable Integer id, @RequestBody @Valid ImageLinkDTO image);

    @DeleteMapping("/{id}/prices/{idPrice}")
    MltResponse<ProductDTO> removePrice(@PathVariable Integer id, @PathVariable Integer idPrice);

    @DeleteMapping("/{id}/categories/{idCategory}")
    MltResponse<ProductDTO> removeCategory(@PathVariable Integer id, @PathVariable Integer idCategory);

    @DeleteMapping("/{id}/images/{idImage}")
    MltResponse<ProductDTO> removeImage(@PathVariable Integer id, @PathVariable Integer idImage);


}
