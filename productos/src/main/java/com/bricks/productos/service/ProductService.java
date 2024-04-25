package com.bricks.productos.service;

import com.bricks.productos.DTO.ProductDTO;
import com.bricks.productos.exception.ProductNotFoundException;
import com.bricks.productos.mapper.ProductMapper;
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
}
