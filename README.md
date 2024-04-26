# Challenge Bricks

API de Administración de Productos

Esta API proporciona servicios para administrar los productos de un comercio, incluyendo la creación, actualización, eliminación y recuperación de productos, así como la obtención de una lista de categorías.

## Requisitos Técnicos

- Desarrollado en Java JDK 17 con Spring Boot y Maven.
- Base de datos en memoria H2.
- Integración con una API externa para obtener las categorías.

## Ejecución del Proyecto

1. Clona el repositorio desde GitHub o GitLab.
2. Abre el proyecto en tu IDE favorito.
3. Ejecuta el proyecto utilizando Gradle.
4. Accede a la API desde el navegador o herramientas como Postman usando la URL [http://localhost:8082](http://localhost:8082).
5. Swagger URL [http://localhost:8082/doc/swagger-ui/index.html](http://localhost:8082/doc/swagger-ui/index.html).



## Endpoints Disponibles

| Método | Ruta              | Descripción                                                       | Ejemplo de Uso                                               |
| ------ | ----------------- | ----------------------------------------------------------------- | ------------------------------------------------------------ |
| GET    | /product          | Obtiene el listado de productos (filtro por name, price, stock y category) (Paginado) | `GET /product?name=producto1&price=10.00&stock=20&category=Gastronomía&page=0&size=10` |
| GET    | /product/{id}     | Obtiene un producto por ID                                        | `GET /product/1`                                             |
| POST   | /product          | Crea un nuevo producto                                             | `POST /product` con cuerpo: { "name": "Pava Electrica", "price": 25.00, "stock": 50, "category": { "code": "GAS",
        "name": "Gastronomía",
        "description": null,
        "icon": "<svg width=\"20\" height=\"20\" viewBox=\"0 0 20 20\" fill=\"none\" xmlns=\"http://www.w3.org/2000/svg\">\r\n<mask id=\"mask0_3874_1229\" style=\"mask-type:alpha\" maskUnits=\"userSpaceOnUse\" x=\"0\" y=\"0\" width=\"20\" height=\"20\">\r\n<rect width=\"20\" height=\"20\" fill=\"#D9D9D9\"/>\r\n</mask>\r\n<g mask=\"url(#mask0_3874_1229)\">\r\n<path d=\"M14.3585 17.9168C14.2403 17.9168 14.1414 17.8769 14.0618 17.797C13.9822 17.7172 13.9424 17.6182 13.9424 17.5002V11.2502H13.2373C12.8671 11.2502 12.5502 11.1184 12.2865 10.8547C12.0229 10.5911 11.8911 10.2742 11.8911 9.90402V5.8335C11.8911 4.90508 12.1531 4.0827 12.6772 3.36635C13.2012 2.65 13.9007 2.23307 14.7757 2.11554V17.5002C14.7757 17.6182 14.7357 17.7172 14.6558 17.797C14.5758 17.8769 14.4767 17.9168 14.3585 17.9168ZM6.66617 17.9168C6.54795 17.9168 6.44905 17.8769 6.36947 17.797C6.28987 17.7172 6.25007 17.6182 6.25007 17.5002V10.388C5.60583 10.2897 5.06016 9.99429 4.61305 9.50177C4.16594 9.00924 3.94238 8.40614 3.94238 7.69248V2.50016C3.94238 2.38211 3.98236 2.28315 4.06232 2.20329C4.14229 2.12343 4.24139 2.0835 4.35961 2.0835C4.47782 2.0835 4.57672 2.12343 4.65632 2.20329C4.73592 2.28315 4.77572 2.38211 4.77572 2.50016V7.69248H6.25007V2.50016C6.25007 2.38211 6.29006 2.28315 6.37003 2.20329C6.44999 2.12343 6.54908 2.0835 6.6673 2.0835C6.78552 2.0835 6.88442 2.12343 6.96401 2.20329C7.04361 2.28315 7.0834 2.38211 7.0834 2.50016V7.69248H8.55776V2.50016C8.55776 2.38211 8.59774 2.28315 8.67772 2.20329C8.75769 2.12343 8.85678 2.0835 8.97499 2.0835C9.09321 2.0835 9.19212 2.12343 9.27172 2.20329C9.3513 2.28315 9.39109 2.38211 9.39109 2.50016V7.69248C9.39109 8.40614 9.16754 9.00924 8.72042 9.50177C8.27331 9.99429 7.72764 10.2897 7.0834 10.388V17.5002C7.0834 17.6182 7.04342 17.7172 6.96345 17.797C6.88349 17.8769 6.7844 17.9168 6.66617 17.9168Z\" fill=\"#868BC3\"/>\r\n</g>\r\n</svg>" } } |
| DELETE | /product/{id}     | Elimina un producto por ID                                        | `DELETE /product/1`                                          |
| PUT    | /product/{id}     | Actualiza un producto por ID                                      | `PUT /product` con cuerpo: { "name": "Producto Actualizado", "price": 30.00, "stock": 100, "category": { "id": 2,
        "code": "VIA",
        "name": "Viajes",
        "description": null,
        "icon": "<svg width=\"20\" height=\"20\" viewBox=\"0 0 20 20\" fill=\"none\" xmlns=\"http://www.w3.org/2000/svg\">\r\n<mask id=\"mask0_3874_1378\" style=\"mask-type:alpha\" maskUnits=\"userSpaceOnUse\" x=\"0\" y=\"0\" width=\"20\" height=\"20\">\r\n<rect width=\"20\" height=\"20\" fill=\"#D9D9D9\"/>\r\n</mask>\r\n<g mask=\"url(#mask0_3874_1378)\">\r\n<path d=\"M7.94887 17.6556C7.81961 17.693 7.7125 17.6781 7.62756 17.6107C7.54263 17.5434 7.50016 17.4462 7.50016 17.3191C7.50016 17.2536 7.51859 17.186 7.55545 17.1164C7.59232 17.0467 7.63532 16.9914 7.68445 16.9505L9.16683 15.8655V10.7053L2.6492 12.6204C2.49643 12.6609 2.36395 12.6414 2.25177 12.5618C2.13959 12.4821 2.0835 12.359 2.0835 12.1925C2.0835 12.109 2.1075 12.0236 2.1555 11.9364C2.20351 11.8491 2.26445 11.7865 2.33831 11.7486L9.16683 7.72452V2.91683C9.16683 2.67609 9.24582 2.47702 9.40379 2.3196C9.56176 2.1622 9.76154 2.0835 10.0031 2.0835C10.2448 2.0835 10.4435 2.1622 10.5995 2.3196C10.7555 2.47702 10.8335 2.67609 10.8335 2.91683V7.72452L17.662 11.7486C17.7359 11.7849 17.7968 11.8465 17.8448 11.9336C17.8928 12.0207 17.9168 12.1068 17.9168 12.1919C17.9168 12.3622 17.8607 12.4863 17.7486 12.5643C17.6364 12.6423 17.5039 12.6609 17.3511 12.6204L10.8335 10.7053V15.8335L12.3159 16.9505C12.365 16.9914 12.408 17.0467 12.4449 17.1164C12.4817 17.186 12.5002 17.2536 12.5002 17.3191C12.5002 17.4462 12.4577 17.5434 12.3728 17.6107C12.2878 17.6781 12.1807 17.693 12.0515 17.6556L10.0002 17.0515L7.94887 17.6556Z\" fill=\"#868BC3\"/>\r\n</g>\r\n</svg>"
    } } |
| GET    | /category         | Obtiene el listado de categorías                                  | `GET /category`                                               |


## Actualización de Categorías

Se analizó la necesidad de actualizar las categorías de forma periódica, teniendo en cuenta que el servicio externo tiene un límite de 10 solicitudes diarias. Para abordar este problema, se implementó una solución mediante un cronjob que se ejecuta tres veces al día.

El cronjob realiza una solicitud al servicio externo para obtener las nuevas categorías. Si la solicitud es exitosa, la tabla de categorías en la base de datos se vacía y se actualiza con las nuevas categorías recibidas. De esta manera, se garantiza que las categorías estén siempre actualizadas y se evita exceder el límite de solicitudes diarias del servicio externo.

## Registro de Logs y Excepciones

Se agregó un mecanismo de registro de logs y manejo de excepciones para facilitar la depuración y el seguimiento de errores en la aplicación. En la sección de logs, se pueden encontrar registros detallados de las operaciones realizadas por la aplicación.

## Observación

Para visualizar la base de datos H2, accede a [http://localhost:8082/h2-console](http://localhost:8082/h2-console) en tu navegador.
- Usuario: admin
- Contraseña: admin
