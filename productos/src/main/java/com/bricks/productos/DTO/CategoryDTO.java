package com.bricks.productos.DTO;

import lombok.Data;

@Data
public class CategoryDTO {
    private Long id;
    private String code;
    private String name;
    private String description;
    private String icon;
    // Getters y setters
}
