# Use a Maven image to build the application
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package

# Use a lightweight JDK image for the final container
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY --from=build /app/runner/target/joblog-backend-service.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]