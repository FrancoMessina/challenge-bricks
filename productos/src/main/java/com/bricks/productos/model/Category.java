package com.bricks.productos.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Table(name = "categories")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
    private String description;
    private String icon;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> products;

    // Métodos para mantener la relación bidireccional
    // Cuando agrego o elimino un producto  de la lista de productos de una categoria
    //actualizo la referencia de categoria en el producto
    //Simplifica logica a futuro
    public void addProduct(Product product) {
        products.add(product);
        product.setCategory(this);
    }

    public void removeProduct(Product product) {
        products.remove(product);
        product.setCategory(null);
    }

    // Getters y setters
}
