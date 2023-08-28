package com.mlt.api.apiproducts.service.impl;

import com.mlt.api.apiproducts.domain.dto.data.ProductDTO;
import com.mlt.api.apiproducts.domain.dto.request.body.CreateProductRequest;
import com.mlt.api.apiproducts.domain.dto.request.body.ImageLinkDTO;
import com.mlt.api.apiproducts.domain.dto.request.body.UpdateProductRequest;
import com.mlt.api.apiproducts.domain.dto.request.query.GetProductsQueryParams;
import com.mlt.api.apiproducts.domain.dto.response.GetProductsData;
import com.mlt.api.apiproducts.mapper.PriceMapper;
import com.mlt.api.apiproducts.mapper.ProductCategoryMapper;
import com.mlt.api.apiproducts.mapper.ProductImageMapper;
import com.mlt.api.apiproducts.mapper.ProductMapper;
import com.mlt.api.apiproducts.model.Category;
import com.mlt.api.apiproducts.model.Price;
import com.mlt.api.apiproducts.model.Product;
import com.mlt.api.apiproducts.model.ProductCategory;
import com.mlt.api.apiproducts.model.ProductImage;
import com.mlt.api.apiproducts.repository.CategoryRepository;
import com.mlt.api.apiproducts.repository.ProductRepository;
import com.mlt.api.apiproducts.repository.specifications.ProductSpecification;
import com.mlt.api.apiproducts.service.ProductService;
import com.mlt.api.common.domain.response.MltResponse;
import com.mlt.api.common.handler.error.exception.notfound.CategoryNotFoundException;
import com.mlt.api.common.handler.error.exception.notfound.ImageNotFoundException;
import com.mlt.api.common.handler.error.exception.notfound.PriceNotFoundException;
import com.mlt.api.common.handler.error.exception.notfound.ProductNotFoundException;
import com.mlt.api.common.handler.error.exception.validation.IdsNotFoundException;
import com.mlt.api.common.handler.error.exception.validation.IdsNotMatchException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;
    private final ProductCategoryMapper productCategoryMapper;
    private final PriceMapper priceMapper;
    private final ProductImageMapper productImageMapper;

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
                                              .page(products.getNumber() + 1)
                                              .size(products.getSize())
                                              .build();

        return MltResponse.<GetProductsData>builder()
                          .data(data)
                          .build();
    }

    @Override
    public MltResponse<ProductDTO> getProductById(Integer id) {
        Product product = getProduct(id);
        return MltResponse.<ProductDTO>builder().data(productMapper.toProductDTO(product)).build();
    }

    @Override
    @Transactional
    public MltResponse<ProductDTO> createProduct(CreateProductRequest productDTO) {


        List<Category> categories = categoryRepository.findAllById(productDTO.getCategories());
        if (categories.size() != productDTO.getCategories().size()) {
            throw IdsNotFoundException.builder(
                    "category",
                    productDTO.getCategories().stream().map(Objects::toString).toList()
            ).build();
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
    @Transactional
    public MltResponse<ProductDTO> updateProduct(Integer id, UpdateProductRequest productDTO) {

        if (!id.equals(productDTO.getId())) {
            throw IdsNotMatchException.builder(List.of(id.toString(), productDTO.getId().toString())).build();
        }

        Product product = getProduct(id);
        if (Objects.nonNull(productDTO.getName()) && !productDTO.getName().isBlank()) {
            product.setName(productDTO.getName());
        }

        if (Objects.nonNull(productDTO.getDescription()) && !productDTO.getDescription().isBlank()) {
            product.setDescription(productDTO.getDescription());
        }

        product.setUpdatedAt(LocalDateTime.now());
        product = productRepository.save(product);

        return MltResponse.<ProductDTO>builder().data(productMapper.toProductDTO(product)).build();
    }

    @Override
    public MltResponse<ProductDTO> deleteProduct(Integer id) {
        Product product = getProduct(id);

        product.setDeletedAt(LocalDateTime.now());
        product = productRepository.save(product);
        return MltResponse.<ProductDTO>builder().data(productMapper.toProductDTO(product)).build();
    }

    @Override
    public MltResponse<ProductDTO> addPrice(Integer id, Double price) {
        Product product = getProduct(id);
        if (product.getPrices().stream().noneMatch(p -> p.getPrice().equals(price))) {
            Price newPrice = priceMapper.toPrice(price);
            product.addPrice(newPrice);
            productRepository.save(product);
        }
        return MltResponse.<ProductDTO>builder().data(productMapper.toProductDTO(product)).build();
    }

    @Override
    public MltResponse<ProductDTO> addCategory(Integer id, Integer idCategory) {
        Product product = getProduct(id);
        if (product.getProductCategories().stream().noneMatch(p -> p.getCategory().getId().equals(idCategory))) {
            Category category = categoryRepository.findById(idCategory)
                                                  .orElseThrow(() -> CategoryNotFoundException.builder(idCategory.toString())
                                                                                              .build());

            ProductCategory productCategory = productCategoryMapper.toProductCategory(category, product);
            product.addCategory(productCategory);

            productRepository.save(product);
        }
        return MltResponse.<ProductDTO>builder().data(productMapper.toProductDTO(product)).build();
    }

    @Override
    public MltResponse<ProductDTO> addImage(Integer id, ImageLinkDTO request) {
        Product product = getProduct(id);

        ProductImage image = productImageMapper.toProductImage(request);
        product.addImage(image);
        productRepository.save(product);

        return MltResponse.<ProductDTO>builder().data(productMapper.toProductDTO(product)).build();
    }

    @Override
    public MltResponse<ProductDTO> removePrice(Integer id, Integer idPrice) {
        Product product = getProduct(id);
        Price price = product.getPrices()
                             .stream()
                             .filter(p -> p.getId().equals(idPrice))
                             .findFirst()
                             .orElseThrow(() -> PriceNotFoundException.builder("price id")
                                                                      .build());
        price.setDeletedAt(LocalDateTime.now());
        productRepository.save(product);
        product.getPrices().remove(price);
        return MltResponse.<ProductDTO>builder().data(productMapper.toProductDTO(product)).build();
    }

    @Override
    public MltResponse<ProductDTO> removeCategory(Integer id, Integer idCategory) {
        Product product = getProduct(id);
        ProductCategory productCategory = product.getProductCategories()
                                                 .stream()
                                                 .filter(p -> p.getCategory().getId().equals(idCategory))
                                                 .findFirst()
                                                 .orElseThrow(() -> CategoryNotFoundException.builder(
                                                         "category id"
                                                 ).build());
        productCategory.setDeletedAt(LocalDateTime.now());
        productRepository.save(product);
        product.getProductCategories().remove(productCategory);
        return MltResponse.<ProductDTO>builder().data(productMapper.toProductDTO(product)).build();
    }

    @Override
    public MltResponse<ProductDTO> removeImage(Integer id, Integer idImage) {
        Product product = getProduct(id);
        ProductImage productImage = product.getImages()
                                           .stream()
                                           .filter(p -> p.getId().equals(idImage))
                                           .findFirst()
                                           .orElseThrow(() -> ImageNotFoundException.builder(
                                                   "image id"
                                           ).build());
        productImage.setDeletedAt(LocalDateTime.now());
        productRepository.save(product);
        product.getImages().remove(productImage);
        return MltResponse.<ProductDTO>builder().data(productMapper.toProductDTO(product)).build();
    }

    private Product getProduct(Integer id) {
        return productRepository.findById(id)
                                .orElseThrow(() -> ProductNotFoundException.builder(id.toString()).build());
    }


}
