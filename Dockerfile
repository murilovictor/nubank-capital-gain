FROM openjdk:8-jdk-alpine

MAINTAINER Murilo Victor
COPY target/nubank-1.0.0.jar nubank-1.0.0.jar
ENTRYPOINT ["java","-jar","/nubank-1.0.0.jar"]
