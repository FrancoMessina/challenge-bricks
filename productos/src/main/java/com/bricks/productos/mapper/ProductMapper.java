package com.bricks.productos.mapper;

import com.bricks.productos.DTO.ProductDTO;
import com.bricks.productos.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class ProductMapper {
    @Autowired
    private CategoryMapper categoryMapper;
    public ProductDTO toDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());
        dto.setCategory(categoryMapper.toDTO(product.getCategory()));
        return dto;
    }

    public Product toEntity(ProductDTO dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        product.setCategory(categoryMapper.toEntity(dto.getCategory()));
        return product;
    }

}
