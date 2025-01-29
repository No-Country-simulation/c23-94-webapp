# Usar una imagen base de Maven para compilar el proyecto
FROM maven:3.8.4-openjdk-17 AS build

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar los archivos de proyecto al contenedor
COPY pom.xml .
COPY src ./src

# Mostrar el contenido del directorio de trabajo
RUN echo "Contenido del directorio de trabajo después de copiar archivos:" && ls -l /app

# Compilar el proyecto y generar el archivo JAR
RUN mvn clean package

# Mostrar el contenido del directorio de trabajo después de la compilación
RUN echo "Contenido del directorio de trabajo después de la compilación:" && ls -l /app/target

# Usar una imagen base de OpenJDK para ejecutar la aplicación
FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Mostrar el contenido del directorio de trabajo antes de copiar el archivo JAR
RUN echo "Contenido del directorio de trabajo antes de copiar el archivo JAR:" && ls -l /app

# Copiar el archivo JAR generado desde la etapa de compilación
COPY --from=build /app/target/*.jar app.jar

# Mostrar el contenido del directorio de trabajo después de copiar el archivo JAR
RUN echo "Contenido del directorio de trabajo después de copiar el archivo JAR:" && ls -l /app

# Exponer el puerto en el que la aplicación se ejecutará
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]