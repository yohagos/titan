package com.titan.product.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsStockRepository extends JpaRepository<ProductsStockEntity, Long> {
}
