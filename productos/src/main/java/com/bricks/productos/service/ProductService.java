package com.bricks.productos.service;

import com.bricks.productos.DTO.CategoryDTO;
import com.bricks.productos.DTO.ProductDTO;
import com.bricks.productos.exception.CategoryNotFoundException;
import com.bricks.productos.exception.ProductNotFoundException;
import com.bricks.productos.mapper.ProductMapper;
import com.bricks.productos.model.Category;
import com.bricks.productos.model.Product;
import com.bricks.productos.repository.IProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
    @Autowired
    private  ProductMapper productMapper;
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private ICategoryService categoryService;
    public Page<ProductDTO> getAllProducts(String name, BigDecimal price, Integer stock, String categoryName, Pageable pageable) {
        logger.info("Obteniendo todos los productos...");



        if (stock != null && stock < 0) {
            logger.error("El stock no puede ser negativo");
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }


        Page<Product> products = productRepository.findAllByFilters(name, price, stock, categoryName, pageable);

        logger.info("Se obtuvieron {} productos", products.getTotalElements());
        return products.map(productMapper::toDTO);

    }

    public ProductDTO getProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            return productMapper.toDTO(product);
        } else {
            logger.warn("No se encontró ningún producto con el ID: {}", id);
            throw new ProductNotFoundException("No se encontró ningún producto con el ID: " + id);
        }
    }
    public void deleteProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("No se encontró el producto con ID: {}", id);
                    return new ProductNotFoundException("Producto no encontrado con ID: " + id);
                });

        productRepository.delete(product);
        logger.info("Producto eliminado con éxito con ID: {}", id);
    }
    public ProductDTO createProduct(ProductDTO productDTO) {
        // Verificar si la categoría del producto existe
        CategoryDTO categoryDTO = productDTO.getCategory();
        if (categoryDTO != null && categoryDTO.getId() != null) {
            Optional<Category> optionalCategory = Optional.ofNullable(categoryService.getCategoryById(categoryDTO.getId()));
            if (optionalCategory.isEmpty()) {
                throw new CategoryNotFoundException("La categoría con ID " + categoryDTO.getId() + " no existe.");
            }
        }

        // Mapear el DTO de producto a una entidad de producto
        Product product = productMapper.toEntity(productDTO);

        // Guardar el producto en la base de datos
        Product savedProduct = productRepository.save(product);

        // Mapear la entidad de producto guardada a un DTO de producto y devolverlo
        return productMapper.toDTO(savedProduct);
    }
    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        // Verificar si el producto existe
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("No se encontró ningún producto con el ID: {}", id);
                    return new ProductNotFoundException("No se encontró ningún producto con el ID: " + id);
                });

        // Verificar si la categoría del producto existe
        validateCategoryExists(productDTO.getCategory());

        // Mapear el DTO de producto actualizado a la entidad de producto existente
        Product updatedProduct = productMapper.toEntity(productDTO);

        // Actualizar los campos del producto existente con los del producto actualizado
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setStock(updatedProduct.getStock());
        existingProduct.setCategory(updatedProduct.getCategory());

        // Guardar el producto actualizado en la base de datos
        updatedProduct = productRepository.save(existingProduct);

        // Mapear la entidad de producto actualizada a un DTO y devolverlo
        return productMapper.toDTO(updatedProduct);
    }


    private void validateCategoryExists(CategoryDTO categoryDTO) {
        if (categoryDTO != null && categoryDTO.getId() != null) {
            Optional<Category> optionalCategory = Optional.ofNullable(categoryService.getCategoryById(categoryDTO.getId()));
            if (optionalCategory.isEmpty()) {
                logger.warn("No se encontró ningúna categoría con el ID: {}", categoryDTO.getId());
                throw new CategoryNotFoundException("La categoría con ID " + categoryDTO.getId() + " no existe.");
            }
        }
    }

}
