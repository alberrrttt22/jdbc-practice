# STAGE 1 - BUILD
FROM eclipse-temurin:21-alpine-3.23 AS builder

WORKDIR /app

COPY pom.xml .
COPY .mvn ./.mvn
COPY ./mvnw .
COPY src ./src

RUN ./mvnw clean package -DskipTests

# STAGE 2 - RUN
FROM eclipse-temurin:21-jre-alpine-3.23

WORKDIR /app

COPY --from=builder /app/target/*.jar afterword.jar

EXPOSE 8080

CMD ["java", "-jar", "afterword.jar"]


