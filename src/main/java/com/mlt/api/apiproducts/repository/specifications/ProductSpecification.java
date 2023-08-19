package com.mlt.api.apiproducts.repository.specifications;

import com.mlt.api.apiproducts.model.Product;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.List;

public class ProductSpecification {

    public static Specification<Product> findByNameLike(String name) {
        return (root, query, cb) -> {
            if (name == null) {
                return null;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("%");
            sb.append(name);
            sb.append("%");
            return cb.like(root.get("name"), sb.toString());
        };
    }

    public static Specification<Product> findByCategoryNamesIn(List<String> names) {
        return (root, query, cb) -> {
            if (names == null) {
                return null;
            }
            return root.get("category").get("name").in(names);
        };
    }

    public static Specification<Product> findByPriceBetween(Double minPrice, Double maxPrice) {
        return (root, query, cb) -> {
            if (minPrice == null && maxPrice == null) {
                return null;
            }
            if (minPrice == null) {
                return cb.lessThanOrEqualTo(root.get("price"), maxPrice);
            }
            if (maxPrice == null) {
                return cb.greaterThanOrEqualTo(root.get("price"), minPrice);
            }
            return cb.between(root.get("price"), minPrice, maxPrice);
        };
    }

    public static Specification<Product> findByCreatedAtBetween(
            LocalDateTime minCreatedAt,
            LocalDateTime maxCreatedAt
    ) {
        return (root, query, cb) -> {
            if (minCreatedAt == null && maxCreatedAt == null) {
                return null;
            }
            if (minCreatedAt == null) {
                return cb.lessThanOrEqualTo(root.get("createdAt"), maxCreatedAt);
            }
            if (maxCreatedAt == null) {
                return cb.greaterThanOrEqualTo(root.get("createdAt"), minCreatedAt);
            }
            return cb.between(root.get("createdAt"), minCreatedAt, maxCreatedAt);
        };
    }

    public static Specification<Product> findByUpdatedAtBetween(
            LocalDateTime minUpdatedAt,
            LocalDateTime maxUpdatedAt
    ) {
        return (root, query, cb) -> {
            if (minUpdatedAt == null && maxUpdatedAt == null) {
                return null;
            }
            if (minUpdatedAt == null) {
                return cb.lessThanOrEqualTo(
                        cb.function("TRUNC", LocalDateTime.class, root.get("updatedAt")),
                        maxUpdatedAt
                );
            }
            if (maxUpdatedAt == null) {
                return cb.greaterThanOrEqualTo(
                        cb.function("TRUNC", LocalDateTime.class, root.get("updatedAt")),
                        minUpdatedAt
                );
            }
            return cb.between(
                    cb.function("TRUNC", LocalDateTime.class, root.get("updatedAt")),
                    minUpdatedAt,
                    maxUpdatedAt
            );
        };
    }

}
