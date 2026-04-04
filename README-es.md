# DInno - Microservicio de Usuarios

**Dinno-Users** es el microservicio central encargado de la gestión de los perfiles de usuario dentro del ecosistema **DInno**. Construido sobre un entorno reactivo completo, este servicio está preparado para soportar peticiones de alta afluencia mientras administra la información de perfil, preferencias de interfaz y configuraciones de notificaciones.

*Leer esto en otros idiomas: [English](README.md)*

## 🚀 Tecnologías Utilizadas
- **Java 21**
- **Spring Boot 3.4** (Reactive WebFlux)
- **Spring Cloud** (Netflix Eureka Discovery Client)
- **Spring Data R2DBC** (Para bases de datos relacionales reactivas)
- **PostgreSQL**
- **Flyway** (Para las migraciones de Base de Datos)
- **Swagger / OpenAPI 3** (Documentación Interactiva de la API)
- **Docker** (Despliegues Multi-etapa)

## 🏗️ Arquitectura y Seguridad
Este microservicio funciona como un servicio *downstream* detrás del **Dinno API Gateway**.
**No** maneja contraseñas, encriptación, ni valida tokens JWT nativamente. En su lugar, confía implícitamente en el enrutamiento del Gateway:
- Cualquiera que sea una solicitud malintencionada es bloqueada por el Gateway antes de llegar acá.
- Las solicitudes válidas llegan a este servicio inyectadas con los Headers HTTP `X-User-Id` y `X-User-Email`.
- Los perfiles de base de datos se rigen por **creación perezosa (lazy creation)**. Si un usuario completamente nuevo consulta su perfil, este microservicio le instanciará los registros predeterminados automáticamente basándose en los Headers del Gateway.

## ⚙️ Desarrollo Local
Para inicializar este microservicio localmente asegura tener una Base de Datos PostgreSQL lista o usa las credenciales en la nube.

1. **Variables de Entorno**:
   Copia los componentes de `.env.example` en `.env` e imputa los valores reales:
   ```bash
   cp .env.example .env
   ```
2. **Ejecución**:
   Corre el programa directamente a través de tu IDE o usando Maven:
   ```bash
   mvn spring-boot:run
   ```
3. **Migraciones de Base de Datos**:
   Flyway construirá las identidades DDL en PostgreSQL valiéndose del script `V1__init_schema.sql` en el arranque de Tomcat/Netty.

## 🐳 Despliegue con Docker
Este microservicio cuenta con un `Dockerfile` optimizado y basado en compilación Multi-etapa.
Para pasarlo a un contenedor local u hostear remotamente:
```bash
docker build -t dinno-users .
docker run --env-file .env -p 8080:8080 dinno-users
```

## 📖 Documentación de la API (Swagger)
En cuanto el aplicativo inicie, podrás acceder interactivamente a los Endpoints vía:
- **URL**: `http://localhost:8080/webjars/swagger-ui/index.html`

> **Nota**: Para ejecutar peticiones de prueba desde tu navegador, oprime el candado **Authorize** e introduce de forma "hardcodeada" tu Identificador (UUID en la parte de X-User-Id) y un Correo en blanco, esto simula el API Gateway de DInno trabajando en retrospectiva.

## 📍 Principales Endpoints
- `GET /users/profile` -> Obtener la estructura del Perfil completo.
- `PUT /users/profile` -> Realizar actualización global de la biografía.
- `PATCH /users/profile/appearance` -> Alternar estado del *Dark Mode*.
- `PATCH /users/profile/notifications` -> Alternar estado de recibo de correos.
