package com.stock.repository;

import com.stock.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "select (select IFNULL(SUM(e.quantity),0) from Entry e where e.product_id=?1)  -  IFNULL( SUM(s.quantity) ,0) from Out s where s.product_id=?1", nativeQuery = true)
    Integer findByQuantidadeProduto(Long idProduto);
}
