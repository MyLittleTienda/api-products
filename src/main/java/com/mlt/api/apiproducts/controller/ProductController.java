package com.mlt.api.apiproducts.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
public interface ProductController {

    @GetMapping("")
    ResponseEntity<?> getProducts();

}
