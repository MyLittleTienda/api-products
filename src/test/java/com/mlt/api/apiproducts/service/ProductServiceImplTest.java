package com.mlt.api.apiproducts.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.mlt.api.apiproducts.domain.dto.data.ProductDTO;
import com.mlt.api.apiproducts.domain.dto.request.body.UpdateProductRequest;
import com.mlt.api.apiproducts.domain.dto.request.query.GetProductsQueryParams;
import com.mlt.api.apiproducts.domain.dto.response.GetProductsData;
import com.mlt.api.apiproducts.model.Category;
import com.mlt.api.apiproducts.model.Price;
import com.mlt.api.apiproducts.model.Product;
import com.mlt.api.apiproducts.model.ProductCategory;
import com.mlt.api.apiproducts.model.ProductImage;
import com.mlt.api.apiproducts.repository.ProductRepository;
import com.mlt.api.apiproducts.service.impl.ProductServiceImpl;
import com.mlt.api.common.domain.response.MltResponse;
import com.mlt.api.common.handler.error.exception.notfound.ProductNotFoundException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceImplTest {

       @Autowired
       private ProductServiceImpl productService;

       @MockBean
       private ProductRepository productRepository;

       @Test
       @SuppressWarnings("unchecked")
       void givenAllParameters_whenGetProducts_thenOk() {
              LocalDateTime now = LocalDateTime.now();
              List<Product> productsMock = new ArrayList<>();
              for (int i = 0; i < 10; i++) {
                     Product product = Product.builder()
                                   .id(i)
                                   .name("Product " + i)
                                   .description("Description " + i)
                                   .prices(List.of(Price.builder()
                                                 .price(1000.0)
                                                 .id(i)
                                                 .createdAt(now)
                                                 .build()))
                                   .images(List.of(ProductImage.builder()
                                                 .imageLink("Link")
                                                 .provider("Provider")
                                                 .createdAt(now)
                                                 .id(i)
                                                 .build()))
                                   .productCategories(List.of(ProductCategory.builder()
                                                 .category(Category.builder()
                                                               .id(i)
                                                               .name("Category")
                                                               .createdAt(now)
                                                               .build())
                                                 .createdAt(now)
                                                 .build()))
                                   .build();
                     productsMock.add(product);
              }

              Page<Product> products = new PageImpl<>(productsMock, PageRequest.of(0, 10), 1);

              Mockito.when(productRepository.findAll(Mockito.any(Specification.class), Mockito.any(Pageable.class)))
                            .thenReturn(products);

              GetProductsQueryParams params = GetProductsQueryParams.builder()
                            .name("Product")
                            .categoryNames(List.of("Category"))
                            .page(1)
                            .size(10)
                            .sort("ASC")
                            .build();

              GetProductsData data = (GetProductsData) productService.getProducts(params).getData();

              assertNotNull(data);

              assertEquals(10, data.getProducts().size());
              assertEquals(1, data.getTotalPages());
              assertEquals(10, data.getTotalElements());
              assertEquals(1, data.getPage());
              assertEquals(10, data.getSize());

       }

       @Test
       @SuppressWarnings("unchecked")
       void givenAllParameters_whenGetProducts_thenNotData() {
              List<Product> productsMock = new ArrayList<>();

              Page<Product> products = new PageImpl<>(productsMock, PageRequest.of(0, 10), 1);

              Mockito.when(productRepository.findAll(Mockito.any(Specification.class), Mockito.any(Pageable.class)))
                            .thenReturn(products);

              GetProductsQueryParams params = GetProductsQueryParams.builder()
                            .name("Product")
                            .categoryNames(List.of("Category"))
                            .page(1)
                            .size(10)
                            .sort("ASC")
                            .build();

              GetProductsData data = (GetProductsData) productService.getProducts(params).getData();

              assertNotNull(data);

              assertEquals(0, data.getProducts().size());
              assertEquals(1, data.getTotalPages());
              assertEquals(1, data.getTotalElements());
              assertEquals(1, data.getPage());
              assertEquals(10, data.getSize());
       }

       @Test
       void whenGetProductById_thenNotFound() {
              Mockito.when(productRepository.findById(Mockito.any()))
                            .thenReturn(Optional.empty());

              assertThrows(ProductNotFoundException.class, () -> productService.getProductById(1));
       }

       @Test
       void whenGetProductById_thenOk() {
              Product productMock = Product.builder()
                            .id(1)
                            .name("Product")
                            .description("Description")
                            .prices(List.of(Price.builder()
                                          .price(1000.0)
                                          .id(1)
                                          .createdAt(LocalDateTime.now())
                                          .build()))
                            .images(List.of(ProductImage.builder()
                                          .imageLink("Link")
                                          .provider("Provider")
                                          .createdAt(LocalDateTime.now())
                                          .id(1)
                                          .build()))
                            .productCategories(List.of(ProductCategory.builder()
                                          .category(Category.builder()
                                                        .id(1)
                                                        .name("Category")
                                                        .createdAt(
                                                                      LocalDateTime.now())
                                                        .build())
                                          .createdAt(LocalDateTime.now())
                                          .build()))
                            .build();

              Mockito.when(productRepository.findById(Mockito.any()))
                            .thenReturn(Optional.of(productMock));

              MltResponse<ProductDTO> result = productService.getProductById(1);

              assertNotNull(result);
              assertEquals(1, result.getData().getId());
              assertEquals("Product", result.getData().getName());
              assertEquals("Description", result.getData().getDescription());
              assertEquals(1, result.getData().getPrices().size());
              assertEquals(1, result.getData().getImages().size());
              assertEquals(1, result.getData().getCategories().size());
       }

       @Test
       void whenDeleteProduct_thenOk() {
              Product mockProduct = Product.builder()
                            .id(1)
                            .name("Product")
                            .description("Description")
                            .prices(List.of(Price.builder()
                                          .price(1000.0)
                                          .id(1)
                                          .createdAt(LocalDateTime.now())
                                          .build()))
                            .images(List.of(ProductImage.builder()
                                          .imageLink("Link")
                                          .provider("Provider")
                                          .createdAt(LocalDateTime.now())
                                          .id(1)
                                          .build()))
                            .productCategories(List.of(ProductCategory.builder()
                                          .category(Category.builder()
                                                        .id(1)
                                                        .name("Category")
                                                        .createdAt(
                                                                      LocalDateTime.now())
                                                        .build())
                                          .createdAt(LocalDateTime.now())
                                          .build()))
                            .build();
              Mockito.when(productRepository.findById(Mockito.any()))
                            .thenReturn(Optional.of(mockProduct));
              Mockito.when(productRepository.save(Mockito.any()))
                            .thenReturn(mockProduct);

              MltResponse<ProductDTO> result = productService.deleteProduct(1);

              assertNotNull(result);
              assertEquals(1, result.getData().getId());
              assertEquals("Product", result.getData().getName());
              assertEquals("Description", result.getData().getDescription());
              assertEquals(1, result.getData().getPrices().size());
              assertEquals(1, result.getData().getImages().size());
              assertEquals(1, result.getData().getCategories().size());
       }

       @Test
       void whenDeleteProduct_thenNotFound() {
              Mockito.when(productRepository.findById(Mockito.any()))
                            .thenReturn(Optional.empty());

              assertThrows(ProductNotFoundException.class, () -> productService.deleteProduct(1));
       }

       @Test
       void whenUpdateProduct_thenOk() {
              Product productMock = Product.builder()
                            .id(1)
                            .name("Product")
                            .description("Description")
                            .prices(List.of(Price.builder()
                                          .price(1000.0)
                                          .id(1)
                                          .createdAt(LocalDateTime.now())
                                          .build()))
                            .images(List.of(ProductImage.builder()
                                          .imageLink("Link")
                                          .provider("Provider")
                                          .createdAt(LocalDateTime.now())
                                          .id(1)
                                          .build()))
                            .productCategories(List.of(ProductCategory.builder()
                                          .category(Category.builder()
                                                        .id(1)
                                                        .name("Category")
                                                        .createdAt(
                                                                      LocalDateTime.now())
                                                        .build())
                                          .createdAt(LocalDateTime.now())
                                          .build()))
                            .build();

              Mockito.when(productRepository.save(Mockito.any()))
                            .thenReturn(productMock);
              Mockito.when(productRepository.findById(Mockito.any()))
                            .thenReturn(Optional.of(productMock));

              MltResponse<ProductDTO> result = productService.updateProduct(1, UpdateProductRequest.builder()
                            .id(1)
                            .name("Product")
                            .description("Description")
                            .build());

              assertNotNull(result);
              assertEquals(1, result.getData().getId());
              assertEquals("Product", result.getData().getName());
              assertEquals("Description", result.getData().getDescription());
              assertEquals(1, result.getData().getPrices().size());
              assertEquals(1, result.getData().getImages().size());
              assertEquals(1, result.getData().getCategories().size());
       }

       @Test
       void whenUpdateProduct_thenNotFound() {
              Mockito.when(productRepository.findById(Mockito.any()))
                            .thenReturn(Optional.empty());
              UpdateProductRequest request = UpdateProductRequest.builder()
                            .id(1)
                            .name("Product")
                            .description("Description")
                            .build();

              assertThrows(ProductNotFoundException.class, () -> productService.updateProduct(1, request));
       }

       @Test
       void whenAddPrice_thenOk() {
              Product productMock = Product.builder()
                            .id(1)
                            .name("Product")
                            .description("Description")
                            .prices(List.of(Price.builder()
                                          .price(1000.0)
                                          .id(1)
                                          .createdAt(LocalDateTime.now())
                                          .build()))
                            .images(List.of(ProductImage.builder()
                                          .imageLink("Link")
                                          .provider("Provider")
                                          .createdAt(LocalDateTime.now())
                                          .id(1)
                                          .build()))
                            .productCategories(List.of(ProductCategory.builder()
                                          .category(Category.builder()
                                                        .id(1)
                                                        .name("Category")
                                                        .createdAt(
                                                                      LocalDateTime.now())
                                                        .build())
                                          .createdAt(LocalDateTime.now())
                                          .build()))
                            .build();

              Mockito.when(productRepository.save(Mockito.any()))
                            .thenReturn(productMock);
              Mockito.when(productRepository.findById(Mockito.any()))
                            .thenReturn(Optional.of(productMock));

              MltResponse<ProductDTO> result = productService.addPrice(1, 1000D);

              assertNotNull(result);
              assertEquals(1, result.getData().getId());
              assertEquals("Product", result.getData().getName());
              assertEquals("Description", result.getData().getDescription());
              assertEquals(1, result.getData().getPrices().size());
              assertEquals(1, result.getData().getImages().size());
              assertEquals(1, result.getData().getCategories().size());
       }

       @Test
       void whenAddPrice_thenNotFound() {
              Mockito.when(productRepository.findById(Mockito.any()))
                            .thenReturn(Optional.empty());

              assertThrows(ProductNotFoundException.class, () -> productService.addPrice(1, 1000D));
       }

       @Test
       void whenAddCategory_thenOk() {
              Product productMock = Product.builder()
                            .id(1)
                            .name("Product")
                            .description("Description")
                            .prices(List.of(Price.builder()
                                          .price(1000.0)
                                          .id(1)
                                          .createdAt(LocalDateTime.now())
                                          .build()))
                            .images(List.of(ProductImage.builder()
                                          .imageLink("Link")
                                          .provider("Provider")
                                          .createdAt(LocalDateTime.now())
                                          .id(1)
                                          .build()))
                            .productCategories(List.of(ProductCategory.builder()
                                          .category(Category.builder()
                                                        .id(1)
                                                        .name("Category")
                                                        .createdAt(
                                                                      LocalDateTime.now())
                                                        .build())
                                          .createdAt(LocalDateTime.now())
                                          .build()))
                            .build();

              Mockito.when(productRepository.findById(Mockito.any()))
                            .thenReturn(Optional.of(productMock));

              MltResponse<ProductDTO> result = productService.addCategory(1, 1);

              assertNotNull(result);
              assertEquals(1, result.getData().getId());
              assertEquals("Product", result.getData().getName());
              assertEquals("Description", result.getData().getDescription());
              assertEquals(1, result.getData().getPrices().size());
              assertEquals(1, result.getData().getImages().size());
              assertEquals(1, result.getData().getCategories().size());
       }

       @Test
       void whenAddCategory_thenNotFound() {
              Mockito.when(productRepository.findById(Mockito.any()))
                            .thenReturn(Optional.empty());

              assertThrows(ProductNotFoundException.class, () -> productService.addCategory(1, 1));
       }

       @Test
       void whenRemovePrice_thenOk() {
              List<Price> prices = new ArrayList<>();
              prices.add(Price.builder().id(1).price(1000D).build());
              Product productMock = Product.builder()
                            .id(1)
                            .name("Product")
                            .description("Description")
                            .prices(prices)
                            .images(List.of())
                            .productCategories(List.of())
                            .build();

              Mockito.when(productRepository.save(Mockito.any()))
                            .thenReturn(productMock);
              Mockito.when(productRepository.findById(Mockito.any()))
                            .thenReturn(Optional.of(productMock));

              MltResponse<ProductDTO> result = productService.removePrice(1, 1);

              assertNotNull(result);
              assertEquals(1, result.getData().getId());
              assertEquals("Product", result.getData().getName());
              assertEquals("Description", result.getData().getDescription());
              assertEquals(0, result.getData().getPrices().size());
              assertEquals(0, result.getData().getImages().size());
              assertEquals(0, result.getData().getCategories().size());
       }

       @Test
       void whenRemoveCategory_thenOk() {
              List<ProductCategory> productCategories = new ArrayList<>();
              productCategories.add(ProductCategory.builder()
                            .id(1)
                            .category(Category.builder().id(1).name("name").build())
                            .build());
              Product productMock = Product.builder()
                            .id(1)
                            .name("Product")
                            .description("Description")
                            .prices(List.of())
                            .images(List.of())
                            .productCategories(productCategories)
                            .build();

              Mockito.when(productRepository.findById(Mockito.any()))
                            .thenReturn(Optional.of(productMock));
              Mockito.when(productRepository.save(Mockito.any()))
                            .thenReturn(productMock);

              MltResponse<ProductDTO> result = productService.removeCategory(1, 1);

              assertNotNull(result);
              assertEquals(1, result.getData().getId());
              assertEquals("Product", result.getData().getName());
              assertEquals("Description", result.getData().getDescription());
              assertEquals(0, result.getData().getPrices().size());
              assertEquals(0, result.getData().getImages().size());
              assertEquals(0, result.getData().getCategories().size());
       }

       @Test
       void whenRemoveImage_thenOk() {
              List<ProductImage> images = new ArrayList<>();
              images.add(
                            ProductImage.builder()
                                          .id(1)
                                          .imageLink("link")
                                          .provider("provider")
                                          .build());
              Product productMock = Product.builder()
                            .id(1)
                            .name("Product")
                            .description("Description")
                            .prices(List.of())
                            .images(images)
                            .productCategories(List.of())
                            .build();

              Mockito.when(productRepository.findById(Mockito.any()))
                            .thenReturn(Optional.of(productMock));
              Mockito.when(productRepository.save(Mockito.any()))
                            .thenReturn(productMock);

              MltResponse<ProductDTO> result = productService.removeImage(1, 1);

              assertNotNull(result);
              assertEquals(1, result.getData().getId());
              assertEquals("Product", result.getData().getName());
              assertEquals("Description", result.getData().getDescription());
              assertEquals(0, result.getData().getPrices().size());
              assertEquals(0, result.getData().getImages().size());
              assertEquals(0, result.getData().getCategories().size());
       }

       @Test
       void whenRemovePrice_thenNotFound() {
              Mockito.when(productRepository.findById(Mockito.any()))
                            .thenReturn(Optional.empty());

              assertThrows(ProductNotFoundException.class, () -> productService.removePrice(1, 1));
       }

       @Test
       void whenRemoveCategory_thenNotFound() {
              Mockito.when(productRepository.findById(Mockito.any()))
                            .thenReturn(Optional.empty());

              assertThrows(ProductNotFoundException.class, () -> productService.removeCategory(1, 1));
       }

       @Test
       void whenRemoveImage_thenNotFound() {
              Mockito.when(productRepository.findById(Mockito.any()))
                            .thenReturn(Optional.empty());

              assertThrows(ProductNotFoundException.class, () -> productService.removeImage(1, 1));
       }

}
