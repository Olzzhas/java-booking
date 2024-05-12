FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/*.jar
COPY ./target/booking-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]