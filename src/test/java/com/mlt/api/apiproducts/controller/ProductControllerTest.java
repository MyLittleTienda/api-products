package com.mlt.api.apiproducts.controller;

import com.mlt.api.apiproducts.domain.dto.data.CategoryDTO;
import com.mlt.api.apiproducts.domain.dto.data.PriceDTO;
import com.mlt.api.apiproducts.domain.dto.data.ProductDTO;
import com.mlt.api.apiproducts.domain.dto.request.body.ImageLinkDTO;
import com.mlt.api.apiproducts.domain.dto.response.GetProductsData;
import com.mlt.api.apiproducts.service.ProductService;
import com.mlt.api.common.domain.response.MltResponse;
import com.mlt.api.common.handler.error.exception.notfound.CategoryNotFoundException;
import com.mlt.api.common.handler.error.exception.notfound.ImageNotFoundException;
import com.mlt.api.common.handler.error.exception.notfound.PriceNotFoundException;
import com.mlt.api.common.handler.error.exception.notfound.ProductNotFoundException;
import com.mlt.api.common.handler.error.exception.validation.IdsNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void givenNotParameters_whenGetProducts_thenThrowValidationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products").contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isBadRequest());
    }

    @Test
    void givenValidParameters_whenGetProducts_thenRespondOk() throws Exception {
        Mockito.when(productService.getProducts(Mockito.any()))
               .thenReturn(MltResponse.<GetProductsData>builder().build());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products?size=10&page=1&sort=ASC")
                                              .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk());
    }

    @Test
    void givenValidParameters_whenGetProductById_thenIsOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products/1").contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk());
    }

    @Test
    void givenInvalidParameters_whenGetProductById_thenNotFoundError() throws Exception {
        Mockito.doThrow(ProductNotFoundException.builder("1").build()).when(productService).getProductById(1);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products/1").contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isNotFound());
    }

    @Test
    void givenInvalidParameters_whenCreateProduct_thenThrowValidationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/products")
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .content("{}"))
               .andExpect(status().isBadRequest())
               .andExpect(jsonPath("$.messages").exists())
               .andExpect(jsonPath("$.messages.size()").value(4));
    }

    @Test
    void givenValidParameters_whenCreateProduct_thenRespondOk() throws Exception {
        Mockito.when(productService.createProduct(Mockito.any()))
               .thenReturn(MltResponse.<ProductDTO>builder()
                                      .data(ProductDTO.builder()
                                                      .id(1)
                                                      .name("Termo")
                                                      .createdAt(
                                                              LocalDateTime.now())
                                                      .categories(
                                                              List.of(CategoryDTO.builder()
                                                                                 .id(1)
                                                                                 .name("category")
                                                                                 .build()))
                                                      .description("Termo")
                                                      .prices(List.of(
                                                              PriceDTO.builder().id(1).value(500D).build()))
                                                      .build())
                                      .build());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/products").contentType(MediaType.APPLICATION_JSON).content(
                       "{" +
                               "    \"categories\": [1]," +
                               "    \"prices\": [500]," +
                               "    \"description\": \"Termo\"," +
                               "    \"name\": \"Termo\"" +
                               "}"))

               .andExpect(status().isOk())
               .andExpect(jsonPath("$.data.id").exists())
               .andExpect(jsonPath("$.data.name").value("Termo"))
               .andExpect(jsonPath("$.data.description").value("Termo"))
               .andExpect(jsonPath("$.data.categories[0].id").value("1"))
               .andExpect(jsonPath("$.data.prices[0].value").value("500.0"));

    }

    @Test
    void givenInvalidParameters_whenUpdateProduct_thenThrowValidationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/products/1").contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isBadRequest());
    }

    @Test
    void givenValidParameters_whenUpdateProduct_thenRespondOk() throws Exception {
        Mockito.when(productService.updateProduct(Mockito.any(), Mockito.any()))
               .thenReturn(MltResponse.<ProductDTO>builder()
                                      .data(ProductDTO.builder()
                                                      .id(1)
                                                      .name("Termo")
                                                      .createdAt(
                                                              LocalDateTime.now())
                                                      .categories(
                                                              List.of(CategoryDTO.builder()
                                                                                 .id(1)
                                                                                 .name("category")
                                                                                 .build()))
                                                      .description("Termo")
                                                      .prices(List.of(
                                                              PriceDTO.builder().id(1).value(500D).build()))
                                                      .build())
                                      .build());
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/products/1")
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .content(
                                                      "{\"id\":1," +
                                                              "    \"categories\": [1]," +
                                                              "    \"prices\": [500]," +
                                                              "    \"description\": \"Termo\"," +
                                                              "    \"name\": \"Termo\"" +
                                                              "}"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.data.id").exists())
               .andExpect(jsonPath("$.data.name").value("Termo"))
               .andExpect(jsonPath("$.data.description").value("Termo"))
               .andExpect(jsonPath("$.data.categories[0].id").value("1"))
               .andExpect(jsonPath("$.data.prices[0].value").value("500.0"));
    }

    @Test
    void givenValidParameters_whenDeleteProduct_thenThrowNotFoundError() throws Exception {
        Mockito.doThrow(IdsNotFoundException.builder("id", List.of("1")).build())
               .when(productService)
               .deleteProduct(Mockito.any());
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/products/1")
                                              .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isBadRequest());
    }

    @Test
    void givenValidParameters_whenDeleteProduct_thenRespondOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/products/1").contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk());
    }

    @Test
    void givenInvalidParameters_whenAddPrice_thenThrowValidationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/products/1/prices")
                                              .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isBadRequest())
               .andExpect(jsonPath("$.messages").exists())
               .andExpect(jsonPath("$.messages.size()").value(1));
    }

    @Test
    void givenValidParameters_whenAddPrice_thenRespondOk() throws Exception {
        Mockito.when(productService.addPrice(Mockito.any(), Mockito.any()))
               .thenReturn(MltResponse.<ProductDTO>builder()
                                      .data(ProductDTO.builder()
                                                      .id(1)
                                                      .name("Termo")
                                                      .description("Termo")
                                                      .createdAt(
                                                              LocalDateTime.now())
                                                      .categories(
                                                              List.of(CategoryDTO.builder()
                                                                                 .id(1)
                                                                                 .name("category")
                                                                                 .build()))
                                                      .prices(List.of(PriceDTO.builder().id(1).value(500D).build()))
                                                      .build()).build());
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/products/1/prices")
                                              .contentType(MediaType.APPLICATION_JSON).param("price", "500"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.data.id").exists())
               .andExpect(jsonPath("$.data.name").value("Termo"))
               .andExpect(jsonPath("$.data.description").value("Termo"))
               .andExpect(jsonPath("$.data.categories[0].id").value("1"))
               .andExpect(jsonPath("$.data.prices[0].value").value("500.0"));
    }

    @Test
    void givenInvalidParameters_whenAddCategory_thenThrowValidationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/products/1/categories")
                                              .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isBadRequest())
               .andExpect(jsonPath("$.messages").exists())
               .andExpect(jsonPath("$.messages.size()").value(1));
    }

    @Test
    void givenValidParameters_whenAddCategory_thenRespondOk() throws Exception {
        Mockito.when(productService.addCategory(Mockito.any(), Mockito.any()))
               .thenReturn(MltResponse.<ProductDTO>builder()
                                      .data(ProductDTO.builder()
                                                      .id(1)
                                                      .name("Termo")
                                                      .description("Termo")
                                                      .createdAt(
                                                              LocalDateTime.now())
                                                      .categories(
                                                              List.of(CategoryDTO.builder()
                                                                                 .id(1)
                                                                                 .name("category")
                                                                                 .build()))
                                                      .prices(List.of(PriceDTO.builder().id(1).value(500D).build()))
                                                      .build()).build());
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/products/1/categories")
                                              .contentType(MediaType.APPLICATION_JSON).param("idCategory", "1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.data.id").exists())
               .andExpect(jsonPath("$.data.name").value("Termo"))
               .andExpect(jsonPath("$.data.description").value("Termo"))
               .andExpect(jsonPath("$.data.categories[0].id").value("1"))
               .andExpect(jsonPath("$.data.prices[0].value").value("500.0"));
    }

    @Test
    void givenInvalidParameters_whenRemoveCategory_thenNotFoundError() throws Exception {
        Mockito.doThrow(CategoryNotFoundException.builder("id").build())
               .when(productService)
               .removeCategory(Mockito.any(), Mockito.any());
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/products/1/categories/1")
                                              .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isNotFound());
    }

    @Test
    void givenValidParameters_whenRemoveCategory_thenRespondOk() throws Exception {
        Mockito.when(productService.removeCategory(Mockito.any(), Mockito.any()))
               .thenReturn(MltResponse.<ProductDTO>builder()
                                      .data(ProductDTO.builder()
                                                      .id(1)
                                                      .name("Termo")
                                                      .description("Termo")
                                                      .createdAt(
                                                              LocalDateTime.now())
                                                      .categories(
                                                              List.of(CategoryDTO.builder()
                                                                                 .id(1)
                                                                                 .name("category")
                                                                                 .build()))
                                                      .prices(List.of(PriceDTO.builder().id(1).value(500D).build()))
                                                      .build()).build());
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/products/1/categories/1")
                                              .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.data.id").exists())
               .andExpect(jsonPath("$.data.name").value("Termo"))
               .andExpect(jsonPath("$.data.description").value("Termo"))
               .andExpect(jsonPath("$.data.categories[0].id").value("1"))
               .andExpect(jsonPath("$.data.prices[0].value").value("500.0"));
    }

    @Test
    void givenInvalidParameters_whenAddImage_thenThrowValidationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/products/1/images")
                                              .contentType(MediaType.APPLICATION_JSON).content("{}"))
               .andExpect(status().isBadRequest())
               .andExpect(jsonPath("$.messages").exists())
               .andExpect(jsonPath("$.messages.size()").value(2));
    }

    @Test
    void givenValidParameters_whenAddImage_thenRespondOk() throws Exception {
        Mockito.when(productService.addImage(Mockito.any(), Mockito.any()))
               .thenReturn(MltResponse.<ProductDTO>builder()
                                      .data(ProductDTO.builder()
                                                      .id(1)
                                                      .name("Termo")
                                                      .description("Termo")
                                                      .createdAt(
                                                              LocalDateTime.now())
                                                      .categories(
                                                              List.of(CategoryDTO.builder()
                                                                                 .id(1)
                                                                                 .name("category")
                                                                                 .build()))
                                                      .prices(List.of(PriceDTO.builder().id(1).value(500D).build()))
                                                      .images(List.of(ImageLinkDTO.builder()
                                                                                  .link("link")
                                                                                  .provider("provider")
                                                                                  .build()))
                                                      .build()).build());
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/products/1/images")
                                              .contentType(MediaType.APPLICATION_JSON).content("{" +
                                                                                                       "    \"link\": \"link\"," +
                                                                                                       "    \"provider\": \"provider\"" +
                                                                                                       "}"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.data.id").exists())
               .andExpect(jsonPath("$.data.name").value("Termo"))
               .andExpect(jsonPath("$.data.description").value("Termo"))
               .andExpect(jsonPath("$.data.categories[0].id").value("1"))
               .andExpect(jsonPath("$.data.prices[0].value").value("500.0"))
               .andExpect(jsonPath("$.data.images[0].link").value("link"))
               .andExpect(jsonPath("$.data.images[0].provider").value("provider"));
    }

    @Test
    void givenInvalidParameters_whenRemoveImage_thenNotFoundError() throws Exception {
        Mockito.doThrow(ImageNotFoundException.builder("id").build())
               .when(productService)
               .removeImage(Mockito.any(), Mockito.any());
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/products/1/images/1")
                                              .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isNotFound());
    }

    @Test
    void givenValidParameters_whenRemoveImage_thenRespondOk() throws Exception {
        Mockito.when(productService.removeImage(Mockito.any(), Mockito.any()))
               .thenReturn(MltResponse.<ProductDTO>builder()
                                      .data(ProductDTO.builder()
                                                      .id(1)
                                                      .name("Termo")
                                                      .description("Termo")
                                                      .createdAt(
                                                              LocalDateTime.now())
                                                      .categories(
                                                              List.of(CategoryDTO.builder()
                                                                                 .id(1)
                                                                                 .name("category")
                                                                                 .build()))
                                                      .prices(List.of(PriceDTO.builder().id(1).value(500D).build()))
                                                      .images(List.of(ImageLinkDTO.builder()
                                                                                  .link("link")
                                                                                  .provider("provider")
                                                                                  .build()))
                                                      .build()).build());

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/products/1/images/1")
                                              .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.data.id").exists())
               .andExpect(jsonPath("$.data.name").value("Termo"))
               .andExpect(jsonPath("$.data.description").value("Termo"))
               .andExpect(jsonPath("$.data.categories[0].id").value("1"))
               .andExpect(jsonPath("$.data.prices[0].value").value("500.0"))
               .andExpect(jsonPath("$.data.images[0].link").value("link"))
               .andExpect(jsonPath("$.data.images[0].provider").value("provider"));
    }

    @Test
    void givenInvalidParameters_whenRemovePrice_thenThrowValidationError() throws Exception {
        Mockito.doThrow(PriceNotFoundException.builder("id").build())
               .when(productService)
               .removePrice(Mockito.any(), Mockito.any());
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/products/1/prices/1")
                                              .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isNotFound());
    }

    @Test
    void givenValidParameters_whenRemovePrice_thenRespondOk() throws Exception {
        Mockito.when(productService.removePrice(Mockito.any(), Mockito.any()))
               .thenReturn(MltResponse.<ProductDTO>builder()
                                      .data(ProductDTO.builder()
                                                      .id(1)
                                                      .name("Termo")
                                                      .description("Termo")
                                                      .createdAt(
                                                              LocalDateTime.now())
                                                      .categories(
                                                              List.of(CategoryDTO.builder()
                                                                                 .id(1)
                                                                                 .name("category")
                                                                                 .build()))
                                                      .prices(List.of(PriceDTO.builder().id(1).value(500D).build()))
                                                      .build()).build());

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/products/1/prices/1")
                                              .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.data.id").exists())
               .andExpect(jsonPath("$.data.name").value("Termo"))
               .andExpect(jsonPath("$.data.description").value("Termo"))
               .andExpect(jsonPath("$.data.categories[0].id").value("1"))
               .andExpect(jsonPath("$.data.prices[0].value").value("500.0"))
               .andExpect(jsonPath("$.data.images[0]").doesNotExist());
    }
}

