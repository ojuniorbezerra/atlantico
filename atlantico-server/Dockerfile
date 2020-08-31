FROM openjdk:8-jdk-alpine
FROM maven:alpine

VOLUME /tmp

# image layer
WORKDIR /app
ADD pom.xml /app
RUN mvn verify clean --fail-never

# Image layer: with the application
COPY . /app
RUN mvn clean package
EXPOSE 8080
COPY target/*.jar /developments/app.jar
ENTRYPOINT ["java","-jar","/developments/app.jar"]