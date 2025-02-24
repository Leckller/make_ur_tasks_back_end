FROM eclipse-temurin:21

WORKDIR /app
COPY . .

RUN ./mvnw clean package

RUN cp target/*.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]