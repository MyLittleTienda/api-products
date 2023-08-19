package com.mlt.api.apiproducts.repository;

import com.mlt.api.apiproducts.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
}