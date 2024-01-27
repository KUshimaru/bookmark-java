FROM maven:3.8.4-openjdk-17 AS build
COPY . .
RUN mvn clean package


FROM eclipse-temurin:17-jdk-alpine
COPY --from=build /target/BookMark-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]