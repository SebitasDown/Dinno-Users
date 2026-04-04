# Etapa 1: Construcción usando Maven
FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder
WORKDIR /app

# Copiamos primero el pom.xml y descargamos dependencias (aprovechando la caché de Docker)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiamos el código fuente y compilamos el JAR
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución (Imagen más ligera)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Extraemos el JAR compilado de la etapa 1
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
