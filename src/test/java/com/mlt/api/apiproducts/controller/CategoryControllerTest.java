package com.mlt.api.apiproducts.controller;

import com.mlt.api.apiproducts.domain.dto.data.CategoryDTO;
import com.mlt.api.apiproducts.domain.dto.response.GetCategoriesData;
import com.mlt.api.apiproducts.service.CategoryService;
import com.mlt.api.common.domain.response.MltResponse;
import com.mlt.api.common.handler.error.exception.notfound.CategoryNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @Test
    void givenValidParameters_whenCreateCategory_thenRespondOk() throws Exception {
        Mockito.when(categoryService.createCategory(Mockito.any()))
               .thenReturn(MltResponse.<CategoryDTO>builder().data(CategoryDTO.builder()
                                                                              .id(1)
                                                                              .name("name")
                                                                              .build()).build());
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/categories")
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .content("{\"name\":\"name\"}"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.data.id").value(1))
               .andExpect(jsonPath("$.data.name").value("name"));
    }

    @Test
    void givenInvalidParameters_whenCreateCategory_thenThrowValidationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/categories")
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .content("{}"))
               .andExpect(status().isBadRequest())
               .andExpect(jsonPath("$.messages").exists())
               .andExpect(jsonPath("$.messages.size()").value(1));
    }

    @Test
    void givenValidParameters_whenGetCategories_thenRespondOk() throws Exception {
        Mockito.when(categoryService.getCategories())
               .thenReturn(MltResponse.<GetCategoriesData>builder().data(GetCategoriesData.builder()
                                                                                          .categories(List.of(
                                                                                                  CategoryDTO.builder()
                                                                                                             .id(1)
                                                                                                             .name("category")
                                                                                                             .build()))
                                                                                          .build()).build());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/categories")
                                              .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.data.categories[0].id").value(1))
               .andExpect(jsonPath("$.data.categories[0].name").value("category"));
    }

    @Test
    void givenIvalidParameter_whenUpdateCategory_thenThrowValidationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/categories/1")
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .content("{}"))
               .andExpect(status().isBadRequest())
               .andExpect(jsonPath("$.messages").exists())
               .andExpect(jsonPath("$.messages.size()").value(2));
    }


    @Test
    void givenValidParameters_whenUpdateCategory_thenRespondOk() throws Exception {
        Mockito.when(categoryService.updateCategory(Mockito.any(), Mockito.any()))
               .thenReturn(MltResponse.<CategoryDTO>builder().data(CategoryDTO.builder()
                                                                              .id(1)
                                                                              .name("name")
                                                                              .build()).build());
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/categories/1")
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .content("{\"id\":1,\"name\":\"name\"}"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.data.id").value(1))
               .andExpect(jsonPath("$.data.name").value("name"));
    }

    @Test
    void givenInvalidParameters_whenDeleteCategory_thenNotFoundError() throws Exception {
        Mockito.doThrow(CategoryNotFoundException.builder("id").build())
               .when(categoryService)
               .deleteCategory(Mockito.any());

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/categories/1")
                                              .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isNotFound());
    }

    @Test
    void givenValidParameters_whenDeleteCategory_thenRespondOk() throws Exception {
        Mockito.when(categoryService.deleteCategory(Mockito.any()))
               .thenReturn(MltResponse.<CategoryDTO>builder().data(CategoryDTO.builder()
                                                                              .id(1)
                                                                              .name("name")
                                                                              .build()).build());

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/categories/1").contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.data.id").value(1))
               .andExpect(jsonPath("$.data.name").value("name"));
    }


}
