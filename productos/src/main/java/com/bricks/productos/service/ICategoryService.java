package com.bricks.productos.service;

import com.bricks.productos.DTO.CategoryDTO;
import com.bricks.productos.DTO.ProductDTO;
import com.bricks.productos.model.Category;

import java.util.List;

public interface ICategoryService {
    List<CategoryDTO> getAllCategories();
    Category getCategoryById(Long id);
}
