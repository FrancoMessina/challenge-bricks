package com.bricks.productos.controller;

import com.bricks.productos.DTO.ProductDTO;
import com.bricks.productos.exception.CategoryNotFoundException;
import com.bricks.productos.exception.ProductNotFoundException;
import com.bricks.productos.model.Product;
import com.bricks.productos.service.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    @GetMapping
    public ResponseEntity<?> getAllProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) BigDecimal price,
            @RequestParam(required = false) Integer stock,
            @RequestParam(required = false) String categoryName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductDTO> products = productService.getAllProducts(name, price, stock, categoryName, pageable);

        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron productos.");
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        try {
            logger.info("Obteniendo producto con ID: {}", id);
            ProductDTO productDTO = productService.getProductById(id);
            return ResponseEntity.ok(productDTO);
        } catch (ProductNotFoundException e) {
            logger.warn("No se encontró ningún producto con el ID: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró ningún producto con el ID: " + id);
        } catch (Exception e) {
            logger.error("Se produjo un error al obtener el producto con ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Se produjo un error al obtener el producto con ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable Long id) {
        try {
            logger.info("Eliminando producto con ID: {}", id);
            productService.deleteProductById(id);
            String message = "Producto con ID " + id + " eliminado correctamente.";
            return ResponseEntity.ok().body(message);
        } catch (ProductNotFoundException e) {
            logger.warn("No se encontró ningún producto con el ID: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró ningún producto con el ID: " + id);
        } catch (Exception e) {
            logger.error("Se produjo un error al eliminar el producto con ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Se produjo un error al eliminar el producto con ID: " + id);
        }
    }
    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO productDTO) {
        try {
            logger.info("Creando un nuevo producto: {}", productDTO);
            ProductDTO createdProductDTO = productService.createProduct(productDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProductDTO);
        } catch (CategoryNotFoundException e) {
            logger.error("La categoría asociada al producto no existe: {}", productDTO.getCategory().getId());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La categoría asociada al producto no existe: " + productDTO.getCategory().getId());
        } catch (Exception e) {
            logger.error("Se produjo un error al crear el producto: {}", productDTO, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Se produjo un error al crear el producto.");
        }
    }
}
