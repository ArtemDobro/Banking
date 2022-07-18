FROM maven:3.8.5-openjdk-18-slim AS builder
WORKDIR /usr/src/
COPY . .
RUN mvn clean install -Dmaven.test.skip
FROM openjdk:17-alpine3.14
WORKDIR /app
COPY --from=builder /usr/src/target/*.jar /app/deposit-service.jar
EXPOSE 8080
CMD ["java", "-jar", "-Dspring.profiles.active=dev", "/app/deposit-service.jar"]
