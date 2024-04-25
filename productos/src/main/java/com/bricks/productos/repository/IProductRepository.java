package com.bricks.productos.repository;

import com.bricks.productos.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT * FROM products p WHERE " +
            "(:name IS NULL OR p.name = :name) " +
            "AND (:price IS NULL OR p.price = :price) " +
            "AND (:stock IS NULL OR p.stock = :stock) " +
            "AND (:categoryName IS NULL OR p.category_id IN (SELECT id FROM categories WHERE name = :categoryName))",
            nativeQuery = true)
    Page<Product> findAllByFilters(String name, BigDecimal price, Integer stock, String categoryName, Pageable pageable);
}
