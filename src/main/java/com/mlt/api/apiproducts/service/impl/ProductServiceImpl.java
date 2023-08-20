package com.mlt.api.apiproducts.service.impl;

import com.mlt.api.apiproducts.domain.dto.data.ProductDTO;
import com.mlt.api.apiproducts.domain.dto.request.body.CreateProductRequest;
import com.mlt.api.apiproducts.domain.dto.request.body.UpdateProductRequest;
import com.mlt.api.apiproducts.domain.dto.request.query.GetProductsQueryParams;
import com.mlt.api.apiproducts.domain.dto.response.GetProductsData;
import com.mlt.api.apiproducts.mapper.ProductCategoryMapper;
import com.mlt.api.apiproducts.mapper.ProductMapper;
import com.mlt.api.apiproducts.model.Category;
import com.mlt.api.apiproducts.model.Product;
import com.mlt.api.apiproducts.model.ProductCategory;
import com.mlt.api.apiproducts.repository.CategoryRepository;
import com.mlt.api.apiproducts.repository.ProductRepository;
import com.mlt.api.apiproducts.repository.specifications.ProductSpecification;
import com.mlt.api.apiproducts.service.ProductService;
import com.mlt.api.common.domain.response.MltResponse;
import com.mlt.api.common.handler.error.exception.IdsNotMatchException;
import com.mlt.api.common.handler.error.exception.ProductNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;
    private final ProductCategoryMapper productCategoryMapper;

    @Override
    public MltResponse<GetProductsData> getProducts(GetProductsQueryParams queryParams) {
        Sort.Direction sort = Sort.Direction.ASC.name()
                                                .equalsIgnoreCase(queryParams.getSort()) ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(queryParams.getPage() - 1, queryParams.getSize(), sort, "createdAt");

        Specification<Product> specifications = ProductSpecification.findByNameLike(queryParams.getName());
        specifications = specifications.and(ProductSpecification.findByCategoryNamesIn(queryParams.getCategoryNames()));
        specifications = specifications.and(ProductSpecification.findByPriceBetween(
                queryParams.getMinPrice(),
                queryParams.getMaxPrice()
        ));
        specifications = specifications.and(ProductSpecification.findByCreatedAtBetween(
                queryParams.getCreatedAfter(),
                queryParams.getCreatedBefore()
        ));
        specifications = specifications.and(ProductSpecification.findByUpdatedAtBetween(
                queryParams.getUpdatedAfter(),
                queryParams.getUpdatedBefore()
        ));

        Page<Product> products = productRepository.findAll(specifications, pageable);

        GetProductsData data = GetProductsData.builder()
                                              .products(products.getContent()
                                                                .stream()
                                                                .map(productMapper::toProductDTO)
                                                                .collect(Collectors.toList()))
                                              .totalPages(products.getTotalPages())
                                              .totalElements(products.getTotalElements())
                                              .page(products.getNumber())
                                              .size(products.getSize())
                                              .build();

        return MltResponse.<GetProductsData>builder()
                          .data(data)
                          .build();
    }

    @Override
    public MltResponse<ProductDTO> getProductById(Long id) {
        Product product = productRepository.findById(id)
                                           .orElseThrow(() -> ProductNotFoundException.builder(id.toString()).build());
        return MltResponse.<ProductDTO>builder().data(productMapper.toProductDTO(product)).build();
    }

    @Override
    @Transactional
    public MltResponse<ProductDTO> createProduct(CreateProductRequest productDTO) {


        List<Category> categories = categoryRepository.findAllById(productDTO.getCategories());
        if (categories.size() != productDTO.getCategories().size()) {
            throw new RuntimeException("Category not found");
        }


        Product product = productMapper.toProduct(productDTO);
        List<ProductCategory> productCategories = categories.stream()
                                                            .map(c -> productCategoryMapper.toProductCategory(
                                                                    c,
                                                                    product
                                                            ))
                                                            .toList();
        product.setProductCategories(productCategories);
        productRepository.save(product);

        return MltResponse.<ProductDTO>builder().data(productMapper.toProductDTO(product)).build();
    }

    @Override
    public MltResponse<ProductDTO> updateProduct(Long id, UpdateProductRequest productDTO) {
        if (id.equals(productDTO.getId())) {
            throw IdsNotMatchException.builder(List.of(id.toString(), productDTO.getId().toString())).build();
        }
        if (!productRepository.existsById(id)) {
            throw ProductNotFoundException.builder(id.toString()).build();
        }
        Product product = productMapper.toProduct(productDTO);
        product = productRepository.save(product);

        return MltResponse.<ProductDTO>builder().data(productMapper.toProductDTO(product)).build();
    }

    @Override
    public MltResponse<ProductDTO> deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found");
        }
        productRepository.deleteById(id);
        return MltResponse.<ProductDTO>builder().build();
    }
}
