FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY target/configserver-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "app.jar"]
