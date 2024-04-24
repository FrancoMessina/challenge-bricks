package com.bricks.productos.apiExterna;
import com.bricks.productos.DTO.CategoryDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.*;

import java.util.List;

@Component
public class ApiClient {

    private static final Logger logger = LoggerFactory.getLogger(ApiClient.class);

    public List<CategoryDTO> fetchDataFromApiBricks() {
        String apiUrl = "https://api.develop.bricks.com.ar/loyalty/category/producer";
        RestTemplate restTemplate = new RestTemplate();

        try {

            ResponseEntity<CategoryDTO[]> response = restTemplate.getForEntity(apiUrl, CategoryDTO[].class);


            HttpStatusCode statusCode = response.getStatusCode();
            if (statusCode == HttpStatus.OK) {
                // La solicitud fue exitosa
                CategoryDTO[] responseBody = response.getBody();
                logger.info("Status code: {}", response.getStatusCode());
                logger.info("Respuesta de la API: {}", responseBody);
                return List.of(responseBody);
            } else {

                logger.warn("La solicitud a la API no fue exitosa. Código de respuesta: {}", statusCode.value());
                return List.of();
            }
        } catch (HttpClientErrorException e) {

            HttpStatusCode statusCode = e.getStatusCode();
            logger.error("Error al hacer la solicitud a la API. Código de error: {}", statusCode.value());
            return List.of();
        } catch (HttpServerErrorException e) {

            HttpStatusCode statusCode = e.getStatusCode();
            logger.error("Error interno del servidor al hacer la solicitud a la API. Código de error: {}", statusCode.value());
            return List.of();
        } catch (RestClientException e) {

            logger.error("Error al hacer la solicitud a la API: {}", e.getMessage());
            return List.of();
        }
    }
}
