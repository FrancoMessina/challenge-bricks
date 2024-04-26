package com.bricks.productos.service;

import com.bricks.productos.DTO.ProductDTO;
import com.bricks.productos.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface IProductService {
    Page<ProductDTO> getAllProducts(String name, BigDecimal price, Integer stock, String categoryName, Pageable pageable);
    ProductDTO getProductById(Long id);
    void deleteProductById(Long id);
    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO updateProduct(Long id, ProductDTO productDTO);
}
