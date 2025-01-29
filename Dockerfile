# Usar una imagen base de Maven para compilar el proyecto
FROM maven:3.8.4-openjdk-21 AS build

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar los archivos de proyecto al contenedor
COPY pom.xml .
COPY src ./src

# Compilar el proyecto y generar el archivo JAR
RUN mvn clean package

# Usar una imagen base de OpenJDK para ejecutar la aplicación
FROM openjdk:21-jdk-slim

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR generado desde la etapa de compilación
COPY --from=build /app/target/c23-94-webapp-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto en el que la aplicación se ejecutará
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]