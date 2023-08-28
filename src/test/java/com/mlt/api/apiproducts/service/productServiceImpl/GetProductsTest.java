package com.mlt.api.apiproducts.service.productServiceImpl;

import com.mlt.api.apiproducts.domain.dto.request.query.GetProductsQueryParams;
import com.mlt.api.apiproducts.domain.dto.response.GetProductsData;
import com.mlt.api.apiproducts.model.Category;
import com.mlt.api.apiproducts.model.Price;
import com.mlt.api.apiproducts.model.Product;
import com.mlt.api.apiproducts.model.ProductCategory;
import com.mlt.api.apiproducts.model.ProductImage;
import com.mlt.api.apiproducts.repository.ProductRepository;
import com.mlt.api.apiproducts.service.ProductService;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GetProductsTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    void givenAllParameters_whenGetProducts_thenOk() {
        //List of Products for mock with generic data
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
    void givenAllParameters_whenGetProducts_thenNotData() {
        //List of Products for mock with generic data
        LocalDateTime now = LocalDateTime.now();
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

}
