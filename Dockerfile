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
# Dockerfile
FROM gradle:8.4-jdk21 AS builder
WORKDIR /app

# Copy Gradle wrapper and build files first to leverage layer caching
COPY gradle gradle
COPY gradlew .
COPY build.gradle settings.gradle ./

# Copy source and run the build
COPY src ./src
RUN chmod +x ./gradlew && ./gradlew clean bootJar -x test --no-daemon

# Runtime image
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
