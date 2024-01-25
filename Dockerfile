FROM maven:4.0.0-openJDK-17 AS build
COPY . .
RUN mvn clean package


FROM eclipse-temurin:17-jdk
COPY --from=build /target/BookMark-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "demo.jar"]