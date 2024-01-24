# Build stage
FROM eclipse-temurin:17-jdk-jammy AS build
WORKDIR /usr/app
COPY pom.xml .
RUN apt-get update && apt-get install -y maven
RUN mvn dependency:go-offline

COPY src /app/src/
RUN mvn clean package -DskipTests

# Package stage
FROM eclipse-temurin:17-jre-jammy
ARG JAR_FILE=target/newsAPI-0.0.1-SNAPSHOT.jar
COPY --from=build /usr/app/${JAR_FILE} newsAPI-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar" , "newsAPI-0.0.1-SNAPSHOT.jar"]


