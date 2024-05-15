FROM openjdk:17-alpine

MAINTAINER 2023_3SOAT-Dev_Rise_G8

COPY build/libs/tech-challenge-ms-producao-1.0.1.jar /app/

EXPOSE 8081

ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "/app/tech-challenge-ms-producao-1.0.1.jar"]
