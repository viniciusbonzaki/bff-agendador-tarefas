# Definir a Imagem do Maven que será utilizada
FROM maven:3.9-eclipse-temurin-17-alpine AS build

# Diretório de Trabalho dentro do Container
WORKDIR /app

# Copiar o arquivo .jar gerado pela Build para o Diretório de Trabalho
COPY . .

# Gerar o Pacote .jar automaticamente
RUN mvn clean install -DskipTests

# Versão do Java
FROM eclipse-temurin:17-alpine

# Diretório de Trabalho dentro do Container
WORKDIR /app

# Copiar o arquivo .jar gerado pela Build para o Diretório de Trabalho
COPY --from=build /app/target/*.jar  app.jar

# Definir a porta em que a Aplicação irá rodar
EXPOSE 8083

# Comando para rodar a Aplicação
CMD ["java", "-jar", "/app/app.jar"]