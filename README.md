# challenge-bricks
API de Administración de Productos
Esta API proporciona servicios para administrar los productos de un comercio, incluyendo la creación, actualización, eliminación y recuperación de productos, así como la obtención de una lista de categorías.

Requisitos Técnicos
Desarrollado en Java JDK 17 con Spring Boot y Maven.
Base de datos en memoria H2.
Integración con una API externa para obtener las categorías.
Para ejecutar el proyecto localmente, sigue los pasos a continuación.
Ejecución del Proyecto
Clona el repositorio desde GitHub o GitLab.
Abre el proyecto en tu IDE favorito.
Ejecuta el proyecto utilizando Gradle.
Accede a la API desde el navegador o herramientas como Postman usando la URL http://localhost:8082.
Swagger URL http://localhost:8082/doc/swagger-ui/index.html
Endpoints Disponibles
Obtener el listado de productos (filtro por name, price, stock y category) (Paginado)Método: GETRuta: /productParámetros:
name (opcional): Nombre del producto para filtrar.
price (opcional): Precio del producto para filtrar.
stock (opcional): Stock del producto para filtrar.
category (opcional): Nombre de la categoría del producto para filtrar.
page (opcional): Número de página para la paginación (predeterminado: 0).
size (opcional): Tamaño de página para la paginación (predeterminado: 10).
Ejemplo de Uso: GET /product?name=producto1&price=10.00&stock=20&category=electrodomésticos&page=0&size=10
Obtener un producto por IDMétodo: GETRuta: /product/{id}Ejemplo de Uso: GET /product/1
Crear un productoMétodo: POSTRuta: /productDatos Requeridos:
name: Nombre del producto.
price: Precio del producto.
stock: Stock del producto.
category: Categoría del producto.
Ejemplo de Uso:
json
POST /product
{
    "name": "Producto Nuevo",
    "price": 25.00,
    "stock": 50,
    "category": {
        "id": 1,
        "name": "Categoría 1"
    }
}
Eliminar un producto por IDMétodo: DELETERuta: /product/{id}Ejemplo de Uso: DELETE /product/1
Actualizar un productoMétodo: PUTRuta: /product/{id}Datos Requeridos:
name: Nombre del producto.
price: Precio del producto.
stock: Stock del producto.
category: Categoría del producto.
Ejemplo de Uso:
json
PUT /product/1
{
    "name": "Producto Actualizado",
    "price": 30.00,
    "stock": 100,
    "category": {
        "id": 2,
        "name": "Categoría 2"
    }
}
Obtener el listado de categoríasMétodo: GETRuta: /categoryEjemplo de Uso: GET /category
Observación
Para visualizar la base de datos H2, accede a http://localhost:8082/h2-console en tu navegador.
user: admin password: admin

