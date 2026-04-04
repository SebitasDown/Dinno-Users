# DInno - Users Microservice

**Dinno-Users** is the core user profile management microservice within the **DInno** ecosystem. Built on a fully reactive stack, this service natively handles high-concurrency requests while serving user profile data, application preferences, and notification settings.

*Read this in other languages: [Español](README-es.md)*

## 🚀 Technologies Used
- **Java 21**
- **Spring Boot 3.4** (Reactive WebFlux)
- **Spring Cloud** (Netflix Eureka Discovery Client)
- **Spring Data R2DBC** (Reactive Relational Database Connectivity)
- **PostgreSQL**
- **Flyway** (Database Migrations)
- **Swagger / OpenAPI 3** (API Documentation)
- **Docker** (Multi-stage deployments)

## 🏗️ Architecture & Security
This microservice behaves as a downstream service integrated behind the **Dinno API Gateway**. 
It **does not** handle passwords or decode JWTs itself. Instead, it places implicit trust in the Gateway routing mechanism:
- All unauthorized access requests are dropped by the Gateway.
- Valid requests reach this service carrying the HTTP Header `X-User-Id` and `X-User-Email`.
- Profiles are created **lazily on the fly**. If an authenticated user queries their profile for the first time, this service automatically provisions their default profile using the injected Gateway headers.

## ⚙️ Local Development
To run this microservice locally, ensure you have a PostgreSQL environment available.

1. **Environment Variables**:
   Copy `.env.example` to `.env` and fill the variables:
   ```bash
   cp .env.example .env
   ```
2. **Execution**:
   Run the service natively using Maven wrapper:
   ```bash
   mvn spring-boot:run
   ```
3. **Database Migrations**:
   Flyway will implicitly construct the PostgreSQL DDL entities via `V1__init_schema.sql` at startup. No manual executions are necessary.

## 🐳 Docker Deployment
This repository contains a unified Multi-stage `Dockerfile`.
To containerize and launch:
```bash
docker build -t dinno-users .
docker run --env-file .env -p 8080:8080 dinno-users
```

## 📖 API Documentation (Swagger)
Once the service spins up, local interactive endpoints can be examined via:
- **URL**: `http://localhost:8080/webjars/swagger-ui/index.html`

> **Note**: To dry-run requests in Swagger, click the **Authorize** lock icon. Due to gateway decoupling, input raw values for `X-User-Id` (UUID format) and `X-User-Email` directly into the prompt box.

## 📍 Primary Endpoints
- `GET /users/profile` -> Fetch the active profile.
- `PUT /users/profile` -> Batch update core biography information.
- `PATCH /users/profile/appearance` -> Alter dark mode preferences.
- `PATCH /users/profile/notifications` -> Alter push notification targets.
