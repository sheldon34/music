#FROM eclipse-temurin:21-jre-alpine
#WORKDIR /app
##
### Copy the jar built locally (adjust the path/pattern as needed)
#COPY build/libs/*.jar app.jar
##
#EXPOSE 8080
##
#ENTRYPOINT ["java", "-jar", "app.jar"]
# builder stage: build the jar with gradle
FROM gradle:8.4-jdk17 AS builder
WORKDIR /app
COPY gradle gradle
COPY gradlew .
COPY build.gradle settings.gradle ./
COPY src ./src
RUN chmod +x ./gradlew && ./gradlew clean bootJar -x test

# runtime stage: copy the built jar
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
