package com.mlt.api.apiproducts.repository;

import com.mlt.api.apiproducts.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Long> {
}