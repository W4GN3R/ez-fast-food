# Use a imagem base do OpenJDK
#FROM openjdk:17-jdk-slim AS build
FROM maven:3.8.5-openjdk-17-slim AS build

# Defina o diretório de trabalho no contêiner
WORKDIR /app

COPY pom.xml . 

COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/ez-fast-food.jar /app/ez-fast-food.jar

# Copie o arquivo JAR gerado para o contêiner
#COPY target/ez-fast-food.jar app.jar

# Exponha a porta em que a aplicação vai rodar
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "ez-fast-food.jar"]