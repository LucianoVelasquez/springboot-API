# Proyecto Spring Boot
_Este proyecto es una aplicación básica de Spring Boot._

🔐 Deploy del proyecto: https://springboot-api-wfnm.onrender.com/swagger-ui/index.html

🚀 Podes visitar el deploy del frontend de este proyecto en: https://login-swagger-api.vercel.app/ 

## Requisitos

- JDK 11 o superior
- Maven 3.6.0 o superior

## Instalacion del Proyecto
```sh
git clone https://github.com/LucianoVelasquez/springboot-API.git
cd springboot-API
```
## Construir el Proyecto

Ejecuta el siguiente comando en el directorio del proyecto para compilar y construir el proyecto:

```sh
mvn clean install
```

Puedes ejecutar la aplicación con el siguiente comando:

```sh
mvn spring-boot:run
```
## Configuración de la Base de Datos
Actualiza el archivo application.properties con los detalles de tu base de datos: (en desarrollo utilize DB H2 podrias usarlo asi)
```sh
spring.datasource.url=jdbc:h2:file:/data/demo
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enable=true

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.generate-ddl=true
```
La aplicación debería estar disponible en http://localhost:8080.
## Documentación de la API
Este proyecto está documentado con Swagger. Una vez que la aplicación esté en ejecución, puedes acceder a la documentación de la API en: http://localhost:8080/swagger-ui.html
## Tecnologias implementadas 

Dillinger uses a number of open source projects to work properly:

- [Spring Boot 3] - Framework para el desarrollo de aplicaciones Java.
- [Java 17] - Versión moderna del lenguaje de programación Java.
- [PostgreSQL] - Sistema de gestión de bases de datos relacional.
- [JWT] - Estándar para la creación de tokens de acceso de forma segura.
- [Spring Security] - Framework para la gestión de seguridad en aplicaciones Spring.
- [Spring Validation] - Biblioteca para la validación de datos en aplicaciones Spring.
- [TypeScript] - Superset de JavaScript que añade tipos estáticos.
- [Next.js] - Framework de React para la construcción de aplicaciones web estáticas y dinámicas.
- [React] - Biblioteca de JavaScript para la construcción de interfaces de usuario.
- [Tailwind CSS] - Framework de CSS para un diseño web rápido y adaptable.

Este proyecto tiene un front realizado con React, podes visitarlo https://github.com/LucianoVelasquez/login-swaggers_apis

## Contactame
🧑‍💻Porftolio https://veldev.vercel.app/

🔗 Linkedin https://www.linkedin.com/in/lv-dev/

