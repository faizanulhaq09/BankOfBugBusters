FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY target/eurekaserver-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8761
ENV SPRING_PROFILES_ACTIVE=docker

ENTRYPOINT ["java", "-jar", "app.jar"]
