# Etapa de build
FROM ubuntu:latest AS build

# Atualizar repositórios e instalar dependências
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk maven

# Copiar o código-fonte para a imagem
COPY . .

# Executar o build do Maven
RUN mvn clean install

# Etapa final (imagem mínima com JDK)
FROM openjdk:17-jdk-slim

# Copiar o arquivo JAR do build para a imagem final
COPY --from=build /target/E-urna-0.0.1-SNAPSHOT.jar app.jar


# Expõe a porta que o aplicativo usará
EXPOSE 8085

ENTRYPOINT ["java", "-jar", "app.jar"]
