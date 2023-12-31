package com.mlt.api.apiproducts.repository;

import java.util.Objects;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.mlt.api.apiproducts.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>, JpaSpecificationExecutor<Category> {

    static Specification<Category> categoryDeletedAtNotNull() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.isNull(root.get("deletedAt"));
    }

    static Specification<Category> categoryNameEquals(String categoryName) {
        return (root, query, criteriaBuilder) -> {
            if (Objects.isNull(categoryName)) {
                return null;
            }
            return criteriaBuilder.equal(
                    criteriaBuilder.upper(root.get("name")),
                    categoryName.toUpperCase());
        };

    }
}