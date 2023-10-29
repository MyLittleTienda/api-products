package com.mlt.api.apiproducts.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.Where;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product")
@Where(clause = "deleted_at is null")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Size(max = 1000)
    @NotNull
    @Column(name = "description", nullable = false, length = 1000)
    private String description;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductImage> images;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductCategory> productCategories;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Price> prices;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public void addPrice(Price price) {
        this.prices.add(price);
        price.setProduct(this);
    }

    public void addCategory(ProductCategory productCategory) {
        this.productCategories.add(productCategory);
        productCategory.setProduct(this);
    }

    public void addImage(ProductImage productImage) {
        this.images.add(productImage);
        productImage.setProduct(this);
    }

}