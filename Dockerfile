# --------------------------------------------------------------------------
# STAGE 1: BUILD (Compilación)
# Utiliza la imagen completa de Maven y JDK para compilar el proyecto
# --------------------------------------------------------------------------
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copia los archivos de configuración de Maven
COPY pom.xml .

# Descarga las dependencias primero (acelera los builds futuros)
RUN mvn dependency:go-offline

# Copia el código fuente
COPY src ./src

# Empaqueta la aplicación en un JAR
RUN mvn clean package -DskipTests

# --------------------------------------------------------------------------
# STAGE 2: RUN (Ejecución)
# Utiliza solo el JRE más ligero (Alpine) para el entorno de producción
# --------------------------------------------------------------------------
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# 1. Instalar herramientas necesarias:
#    - netcat-openbsd: Para realizar el "ping" al puerto de la base de datos (DB_PORT).
#    - bash: Para ejecutar el script de shell (run-app.sh).
RUN apk add --no-cache netcat-openbsd bash

# 2. Copia y da permisos al script de espera
# Este archivo DEBE existir en la misma carpeta que el Dockerfile.
COPY run-app.sh .
RUN chmod +x run-app.sh

# 3. Copia el JAR generado desde el Stage 1.
#    Usamos el comodín (*.jar) para no depender del número de versión.
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

# 4. Establecer el script de espera como el punto de entrada (ENTRYPOINT)
#    Esto garantiza que el script se ejecute ANTES de la aplicación Java.
ENTRYPOINT ["/app/run-app.sh"]

