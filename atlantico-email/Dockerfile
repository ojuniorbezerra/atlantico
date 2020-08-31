FROM openjdk:8-jdk-alpine
FROM maven:3.6.0-jdk-11-slim AS build

VOLUME /tmp

# image layer
WORKDIR /app
ADD pom.xml /app

# Image layer: with the application
COPY . /app

COPY target/*.jar /developments/app.jar
ENTRYPOINT ["java","-jar","/developments/app.jar"]