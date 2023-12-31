package com.stock.repository;

import com.stock.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByCode(String code);

    Optional<Product> findByCode(String code);
}
