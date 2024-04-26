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
| GET    | /product          | Obtiene el listado de productos (filtro por name, price, stock y category) (Paginado) | `GET /product?name=producto1&price=10.00&stock=20&category=electrodomésticos&page=0&size=10` |
| GET    | /product/{id}     | Obtiene un producto por ID                                        | `GET /product/1`                                             |
| POST   | /product          | Crea un nuevo producto                                             | `POST /product` con cuerpo: { "name": "Producto Nuevo", "price": 25.00, "stock": 50, "category": { "id": 1, "name": "Categoría 1" } } |
| DELETE | /product/{id}     | Elimina un producto por ID                                        | `DELETE /product/1`                                          |
| PUT    | /product/{id}     | Actualiza un producto por ID                                      | `PUT /product/1` con cuerpo: { "name": "Producto Actualizado", "price": 30.00, "stock": 100, "category": { "id": 2, "name": "Categoría 2" } } |
| GET    | /category         | Obtiene el listado de categorías                                  | `GET /category`                                               |

## Observación

Para visualizar la base de datos H2, accede a [http://localhost:8082/h2-console](http://localhost:8082/h2-console) en tu navegador.
- Usuario: admin
- Contraseña: admin
