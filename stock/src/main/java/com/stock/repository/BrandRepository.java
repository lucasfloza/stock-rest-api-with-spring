package com.stock.repository;

import com.stock.domain.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    boolean existsByCode(String code);

    Optional<Brand> findByCode(String code);
}
