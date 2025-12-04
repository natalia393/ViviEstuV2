# -------------------------------------------------------------------
# ETAPA 1: BUILD (Construcción)
# Usamos una imagen con el JDK completo para compilar
# -------------------------------------------------------------------
FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /app

# 1. Copiamos solo los archivos de configuración de Maven primero.
# Esto permite que Docker "recuerde" (cachee) las dependencias si no has cambiado el pom.xml
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Damos permisos de ejecución al wrapper de Maven (necesario si vienes de Windows)
RUN chmod +x mvnw

# 2. Descargamos las librerías. Si no cambias el pom.xml, este paso no se repite.
RUN ./mvnw dependency:go-offline

# 3. Copiamos el código fuente de tu proyecto
COPY src ./src

# 4. Compilamos y generamos el JAR (Saltamos los tests para agilizar el build en Docker)
RUN ./mvnw clean package -DskipTests

# -------------------------------------------------------------------
# ETAPA 2: RUN (Imagen Final)
# Usamos una imagen JRE (Runtime Environment), mucho más ligera
# -------------------------------------------------------------------
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Por seguridad, creamos un grupo y usuario limitado (no root) para correr la app
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Copiamos DESDE la etapa "builder" solo el archivo JAR generado
# Nota: Spring Boot suele generar el jar en target/
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]