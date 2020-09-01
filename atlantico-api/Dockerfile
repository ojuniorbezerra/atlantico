FROM openjdk:8-jdk-alpine
FROM maven:3.6.0-jdk-11-slim AS build

VOLUME /tmp

# image layer
WORKDIR /app
ADD pom.xml /app

## Add the wait script to the image
ADD https://github.com/ufoscout/docker-compose-wait/releases/download/2.7.3/wait /wait
RUN chmod +x /wait


# Image layer: with the application
COPY . /app
EXPOSE 8080
COPY target/*.jar /developments/app.jar
CMD /wait
ENTRYPOINT ["java","-jar","/developments/app.jar"]