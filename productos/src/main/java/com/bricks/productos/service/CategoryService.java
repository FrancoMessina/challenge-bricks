package com.bricks.productos.service;

import com.bricks.productos.DTO.CategoryDTO;
import com.bricks.productos.exception.CategoryNotFoundException;
import com.bricks.productos.mapper.CategoryMapper;
import com.bricks.productos.model.Category;
import com.bricks.productos.repository.ICategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService implements  ICategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(categoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Category getCategoryById(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            return optionalCategory.get();
        } else {
            logger.warn("No se encontró ningúna categoria con el ID: {}", id);
            throw new CategoryNotFoundException("No se encontró ningún categoria con el ID: " + id);
        }
    }

}
