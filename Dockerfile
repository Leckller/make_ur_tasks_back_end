# 1º estágio
# chamado `build-image`, imagem JDK temporária para compilar o código
FROM eclipse-temurin:21-jdk-jammy as build-image

# define o diretório de trabalho
WORKDIR /app

# copia o código-fonte para o diretório de trabalho
COPY . .

# executa o comando de empacotamento do maven
RUN ./mvnw clean package

# 2º estágio
# com imagem JRE, limpa e mais leve
FROM eclipse-temurin:21-jre-jammy

# copia o jar gerado no primeiro estágio para o diretório de trabalho
COPY --from=build-image /app/target/*.jar /app/app.jar

# expõe a porta 8080
EXPOSE 8080

# define o comando de execução da aplicação
ENTRYPOINT ["java", "-jar", "/app/app.jar"]