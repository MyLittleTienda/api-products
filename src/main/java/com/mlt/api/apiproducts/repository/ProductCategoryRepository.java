package com.mlt.api.apiproducts.repository;

import com.mlt.api.apiproducts.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
}