FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY target/account-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8083
ENV SPRING_PROFILES_ACTIVE=docker
ENTRYPOINT ["java", "-jar", "app.jar"]
