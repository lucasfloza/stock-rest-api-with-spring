package com.stock.repository;

import com.stock.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByCode(String code);

    Optional<Category> findByCode(String code);
}
