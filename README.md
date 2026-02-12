# ForoHub - Challenge Alura Latam (G9-ONE)
¡Bienvenido al proyecto ForoHub!

Esta aplicación te permite registrar y autenticar usuarios mediante
JSON Web Token (JWT), una vez autenticado podrás crear temas de discusión, 
listar los temas existentes, actualizar algún tema que ya esté creado o 
eliminarlo. 

## Tabla de Contenidos
- [Descripción](#descripción)
- [Funcionalidades](#funcionalidades)
- [Arquitectura del proyecto](#arquitectura-del-proyecto)
- [Tecnologías utilizadas](#tecnologías-utilizadas)
- [Seguridad](#seguridad)
- [Configuración del proyecto](#configuración-del-proyecto)
- [Capturas de pantalla](#capturas-de-pantalla)
- [Mejoras futuras](#mejoras-futuras)
- [Contribución](#contribución)
- [Créditos](#créditos)


## Descripción
Esta aplicación fue desarrollada como parte del curso
"**Practicando Spring Framework: Challenge Foro Hub**" en Alura Latam.
Permite a los usuarios gestionar temas de discusión en un foro en línea


## Funcionalidades
- **Usuario**  
  - Registro de usuario
  - Login con generación de JWT
  - Protección de endpoints con Spring Security


- **Tópicos (temas)**
    - Crear tópico
    - Validación de datos con Bean Validation
    - Prevención de tópicos duplicados (mismo título y mensaje)
    - Fecha de creación automática
    - Status asignado por el backend
    - Listado con paginación
    - Filtros dinámicos
    - Respuestas HTTP controladas mediante ResponseEntity


- **Manejo de Respuestas HTTP**<br>
La API utiliza ResponseEntity para devolver códigos HTTP adecuados:
  - 200 Ok
  - 201 Created
  - 400 Bad Request
  - 403 Forbidden
  - 404 Not Found


- **Paginación y filtros**<br>
El listado de tópicos soporta:
  - Paginación con Pageable
  - Ordenamiento
  - Filtros por curso en orden alfabetico


- **Manejo Global de Excepciones**<br>
Se implementó un gestor global mediante @RestControllerAdvice, que permite:
  - Centralizar errores


- **Base de datos**<br>
  - Gestionada mediante Flyway con migraciones versionadas
  - Las tablas actuales son: usuarios y topicos


- **Endpoints principales**
 
```bash
  Iniciar sesión
      POST http://localhost:8080/login
  ```

```bash
  Registro de tópico
      POST http://localhost:8080/topicos
  ```

```bash
  Lista de tópico
      GET http://localhost:8080/topicos
  ```

```bash
  Detallar tópico
      GET http://localhost:8080/topicos
  ```

```bash
  Actualizar tópico
      PUT http://localhost:8080/topicos
  ```

```bash
  Eliminar tópico
      DELETE http://localhost:8080/topicos
  ```
  

## Arquitectura del proyecto
El proyecto sigue una arquitectura en capas: 
Controller → Domain → Infra


## Tecnologías utilizadas
- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- Flyway
- MySQL
- Variables de entorno
- Lombok
- Bean Validation
- Spring Security
- JWT (JSON Web Token)


## Seguridad
La API implementa autenticación basada en JWT, el flujo de autenticación
es el siguiente:
1. Registrar usuario
2. Iniciar sesión
3. Obtener token JWT
4. Enviar autenticación como Bearer Token
5. Acceder al endpoint


## Configuración del proyecto
Para ejecutar este proyecto, sigue estos pasos:

1. Clona el repositorio:

    ```bash
    git clone [URL del repositorio]
    ```
2. Crea la base de datos MySQL antes de ejecutar la aplicación, 
las migrations con flyway darán error si no está creada

    ```bash
    CREATE DATABASE forohub_api;
    ```
   
3. Configura tu base de datos en el archivo `application.properties`,
   ubicado dentro del paquete "**resources**" con tus propias variables de entorno
```properties
spring.datasource.url=jdbc:mysql://${DB_HOST}/forohub_api
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASS}

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

server.error.include-stacktrace=never

api.security.token.secret=${JWT_SECRET:12345678}
```
Asegúrate de configurar tus propias variables de entorno y crear tu base de
datos a la cual se conectara la aplicación al ejecutarse.

4. Ejecuta la aplicación con Maven:
    ```bash
    mvn spring-boot:run
    ```

5. **Accede a la Aplicación:**


    *   Abre tu navegador y visita `http://localhost:8080`.
También puedes usar postman o insomnia para mandar las requests


## Capturas de pantalla
--PONER CAPTURAS


## Mejoras futuras
- Implementación de respuestas a tópicos
- Tabla de cursos
- Tabla de perfiles
- Relaciones entre entidades
- Documentación con Swagger / OpenAPI
- Tests unitarios e integración
- Implementación del frontend


## Contribución
¡Las contribuciones son bienvenidas! Si deseas mejorar este proyecto,
sigue estos pasos:
1.  Haz un fork del repositorio.
2.  Crea una rama con tu mejora: `git checkout -b mi-mejora`
3.  Realiza tus cambios y commitea: `git commit -m "Añade mi mejora"`
4.  Sube los cambios a tu repositorio: `git push origin mi-mejora`
5.  Crea un pull request.


## Créditos
Este proyecto es el segundo y último challenge 
de la formación Java y Spring Framework G9 - ONE,
fue desarrollado por **Cecilia Barranco**.
