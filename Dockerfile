# Use a imagem base do OpenJDK
FROM openjdk:17-jdk-alpine

# Defina o diretório de trabalho no contêiner
WORKDIR /app

# Copie o arquivo JAR gerado para o contêiner
COPY target/ez-fast-food.jar app.jar

# Copie o arquivo docker-compose.yml para o contêiner
COPY docker-compose.yml /app/docker-compose.yml

# Exponha a porta em que a aplicação vai rodar
EXPOSE 8080

# Instale o Docker Compose no contêiner
RUN apk add --no-cache curl \
    && curl -L "https://github.com/docker/compose/releases/download/$(curl -s https://api.github.com/repos/docker/compose/releases/latest | grep tag_name | cut -d '"' -f 4)/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose \
    && chmod +x /usr/local/bin/docker-compose

# Comando para rodar o Docker Compose
ENTRYPOINT ["sh", "-c", "docker-compose -f /app/docker-compose.yml up"]

