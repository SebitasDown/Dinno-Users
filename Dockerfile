# Utiliza una imagen ligera de Java 21 para correr la app
FROM eclipse-temurin:21-jre-alpine

# Define el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el .jar compilado del host al contenedor
# Nota: Para esto, antes debes hacer ejecutado 'mvn clean package -DskipTests'
COPY target/*.jar app.jar

# Expone el puerto por defecto de Spring Boot
EXPOSE 8080

# Comando para arrancar el aplicativo
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
