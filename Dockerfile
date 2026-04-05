# Etapa 1: Build con Gradle 8.14.2 y JDK 21 (Compilación)
FROM gradle:8.14.2-jdk21 AS build
COPY --chown=gradle:gradle . /app
WORKDIR /app
RUN gradle bootJar --no-daemon

# Etapa 2: Runtime con JDK 21 (Ejecución)
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copiamos el archivo .jar generado en la etapa anterior
COPY --from=build /app/build/libs/*.jar platzi-play.jar

# Exponemos el puerto donde corre tu app
EXPOSE 8090

# Ejecutamos la aplicación activando el perfil de producción
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "platzi-play.jar"]