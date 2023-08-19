package com.mlt.api.apiproducts.repository;

import com.mlt.api.apiproducts.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}