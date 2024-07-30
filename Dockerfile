# Use a imagem base do OpenJDK
FROM openjdk:17-jdk-alpine

# Defina o diretório de trabalho no contêiner
WORKDIR /app

# Copie o arquivo JAR gerado para o contêiner
COPY target/ez-fast-food.jar app.jar

# Exponha a porta em que a aplicação vai rodar
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
