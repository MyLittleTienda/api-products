package com.mlt.api.apiproducts.controller;

import com.mlt.api.apiproducts.service.ProductService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductService productService;

    @SneakyThrows
    @Test
    void giveNotParameters_whenGetProducts_thenThrowValidationError() {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products"))
               .andExpect(status().isBadRequest());
    }

    @SneakyThrows
    @Test
    void giveInvalidParameters_whenGetProducts_thenThrowValidationError() {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products?size=0&page=0"))
               .andExpect(status().isBadRequest());
    }

    @SneakyThrows
    @Test
    void giveValidParameters_whenGetProducts_thenThrowValidationError() {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products?size=10&page=1&sort=ASC"))
               .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    void giveValidParameters_whenGetProductById_thenThrowValidationError() {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products/1"))
               .andExpect(status().isOk());
    }


}
