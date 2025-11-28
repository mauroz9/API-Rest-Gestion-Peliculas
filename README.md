# Gestion de Peliculas - API REST

Este proyecto consiste en una API REST desarrollada con Spring Boot para la gestión de Películas y, los Acores y Directores que las componen. La aplicación permite administrar Directores, Películas y Actores, gestionando las relaciones entre estas entidades y aplicando reglas de negocio específicas para asegurar la integridad de los datos.

## Tecnologias

El proyecto ha sido desarrollado utilizando las siguientes tecnologías y herramientas:

* **Java 21**: Lenguaje de programación.
* **Spring Boot 3.5.8**: Framework principal para el desarrollo de la API.
* **Spring Data JPA**: Para la persistencia y acceso a datos.
* **H2 Database**: Base de datos en memoria para desarrollo y pruebas.
* **Lombok**: Librería para reducir el código repetitivo (boilerplate).
* **OpenAPI / Swagger**: Para la documentación interactiva de la API.
* **Maven**: Gestor de dependencias y construcción del proyecto.

## Instalacion y Ejecucion

El proyecto incluye el wrapper de Maven (`mvnw`), por lo que no es necesario tener Maven instalado globalmente en el sistema.

### Pasos para ejecutar:

1.  Clonar el repositorio en tu máquina local.
2.  Abrir una terminal en la carpeta raíz del proyecto.
3.  Ejecutar el siguiente comando para compilar y arrancar la aplicación:

    ```bash
    ./mvnw spring-boot:run
    ```

    (En Windows, utiliza `mvnw.cmd spring-boot:run` o simplemente `mvn spring-boot:run` si tienes Maven instalado).

La aplicación se iniciará por defecto en el puerto `8080`.

## Documentacion de la API

La API cuenta con documentación interactiva generada automáticamente. Una vez iniciada la aplicación, puedes acceder a ella a través de las siguientes URLs:

* **Swagger UI (Interfaz visual):** http://localhost:8080/swagger-ui/index.html
* **OpenAPI JSON (Especificación):** http://localhost:8080/v3/api-docs

Además, en la raíz del repositorio se incluye el archivo `gestion-de-peliculas.postman_collection.json`, que contiene una colección de peticiones lista para importar en Postman y probar los endpoints.

## Modelo de Datos

El sistema modela las siguientes entidades y relaciones:

1.  **Director**: Entidad principal.
    * Campos: ID, Nombre, Año de Nacimiento.
    * Relación: Un director puede dirigir múltiples películas.
2.  **Pelicula**: Entidad secundaria.
    * Campos: ID, Título (único), Género, Fecha de Estreno.
    * Relación: Pertenece a un director y tiene múltiples actores.
3.  **Actor**: Entidad de reparto.
    * Campos: ID, Nombre.
    * Relación: Puede participar en múltiples películas.

## Endpoints Principales

### Directores
* `GET /api/v1/directores`: Listar todos los directores.
* `GET /api/v1/directores/{id}`: Obtener el detalle de un director.
* `POST /api/v1/directores`: Crear un nuevo director.
* `PUT /api/v1/directores/{id}`: Editar datos de un director.
* `DELETE /api/v1/directores/{id}`: Eliminar un director (solo si no tiene películas).

### Actores
* `GET /api/v1/actores`: Listar todos los actores.
* `GET /api/v1/actores/{id}`: Obtener detalle de un actor.
* `POST /api/v1/actores`: Crear un nuevo actor.
* `PUT /api/v1/actores/{id}`: Editar un actor.
* `DELETE /api/v1/actores/{id}`: Eliminar un actor.

### Peliculas
* `GET /api/v1/peliculas`: Listar todas las películas.
* `GET /api/v1/peliculas/{id}`: Obtener una película (incluye datos del director y lista de actores).
* `POST /api/v1/peliculas`: Crear una película (requiere indicar el ID de un director existente).
* `PUT /api/v1/peliculas/{id}`: Editar una película.
* `DELETE /api/v1/peliculas/{id}`: Eliminar una película.
* `POST /api/v1/peliculas/{idPelicula}/actores/{idActor}`: Añadir un actor al reparto de una película.

## Reglas de Negocio y Errores

El sistema implementa un manejo de errores centralizado (`GlobalErrorHandle`) que devuelve respuestas ProblemDetail ante las siguientes situaciones:

* **400 Bad Request**:
    * Si se intenta registrar un director que es menor de edad (menor de 18 años).
    * Si al crear una película, el director era menor de edad en la fecha de estreno.
* **404 Not Found**: Cuando no se encuentra la entidad solicitada (Director, Actor o Película).
* **409 Conflict**:
    * Al intentar crear una película con un título que ya existe.
    * Al intentar añadir un actor a una película en la que ya está incluido.
    * Al intentar borrar un director que tiene películas asociadas.

## Base de Datos

El proyecto utiliza **H2** como base de datos en memoria. Esto significa que los datos persistidos se perderán al detener la aplicación.

* **Consola H2**: http://localhost:8080/h2-console
* **JDBC URL**: `jdbc:h2:mem:testdb`
* **Usuario**: `sa`
* **Contraseña**: (dejar en blanco)
