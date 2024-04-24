package com.bricks.productos.job;

import com.bricks.productos.DTO.CategoryDTO;
import com.bricks.productos.model.Category;
import com.bricks.productos.repository.ICategoryRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategorySyncJob {

    @Autowired
    private ICategoryRepository categoryRepository;
    @PostConstruct
    public void init() {
        // Ejecutar el método de sincronización al iniciar el proyecto
        // Esto lo hago por metodos  practicos tambien del proyecto
        this.syncCategoriesFromApi();
    }

    @Scheduled(cron = "0 0 */2 * * *") // Ejecutar cada 2 horas
    public void syncCategoriesFromApi() {
        String apiUrl = "https://api.develop.bricks.com.ar/loyalty/category/producer";
        RestTemplate restTemplate = new RestTemplate();

        // Hago GET a la API para  mapear directamente a una lista de CategoryDTO
        List<CategoryDTO> categories = List.of(restTemplate.getForObject(apiUrl, CategoryDTO[].class));

        if (!categories.isEmpty()) { // Esto lo hago por si  hay una falla en la request
            // Eliminar todas las categorías existentes y guardar las nuevas
            categoryRepository.deleteAllInBatch(); // Eliminar todas las categorías en una sola consulta
            categoryRepository.saveAll(categories.stream().map(this::mapToCategory).collect(Collectors.toList()));
            System.out.println("Categorías sincronizadas correctamente desde la API."); // Hago estos system, pero en un proyecto real serian logs.
        } else {
            System.out.println("No se encontraron categorías en la API.");
        }
    }

    private Category mapToCategory(CategoryDTO categoryDTO) {
        return new Category(categoryDTO.getCode(), categoryDTO.getName(), categoryDTO.getDescription(), categoryDTO.getIcon());
    }
}
