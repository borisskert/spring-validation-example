FROM openjdk:17-jdk-alpine

MAINTAINER https://github.com/borisskert/spring-validation-example

RUN mkdir -p /opt/app
WORKDIR /opt/app

COPY target/spring-validation-example.jar app.jar
COPY docker/start.sh start.sh

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

ENTRYPOINT ["./start.sh"]
