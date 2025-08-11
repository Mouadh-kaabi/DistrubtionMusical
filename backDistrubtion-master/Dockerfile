# Backend Dockerfile (Spring Boot - Version simple)
FROM openjdk:8-jdk-alpine


# Assure-toi que le JAR est bien dans target/
COPY target/SmartMusicNew-0.0.1-SNAPSHOT.jar app.jar


ENTRYPOINT ["java", "-jar", "/app.jar"]
