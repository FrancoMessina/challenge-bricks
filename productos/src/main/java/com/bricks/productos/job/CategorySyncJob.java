package com.bricks.productos.job;

import com.bricks.productos.DTO.CategoryDTO;
import com.bricks.productos.apiExterna.ApiClient;
import com.bricks.productos.model.Category;
import com.bricks.productos.repository.ICategoryRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategorySyncJob {

    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private ApiClient apiClient;

    private static final Logger logger = LoggerFactory.getLogger(CategorySyncJob.class);
    @PostConstruct
    public void init() {
        // Ejecutar el método de sincronización al iniciar el proyecto
        // Esto lo hago por metodos  practicos tambien del proyecto
        this.syncCategoriesFromApi();
    }

    @Scheduled(cron = "0 0 */2 * * *") // Ejecutar cada 2 horas // cambiar este cron manualemente
    public void syncCategoriesFromApi() {
        List<CategoryDTO> categories = apiClient.fetchDataFromApiBricks();

        if (!categories.isEmpty()) {
            categoryRepository.deleteAllInBatch();
            categoryRepository.saveAll(categories.stream().map(this::mapToCategory).collect(Collectors.toList()));
            logger.info("Categorías sincronizadas correctamente desde la API.");
        } else {
            logger.warn("No se encontraron categorías en la API.");
        }
    }

    private Category mapToCategory(CategoryDTO categoryDTO) {
        return new Category(categoryDTO.getCode(), categoryDTO.getName(), categoryDTO.getDescription(), categoryDTO.getIcon());
    }
}
