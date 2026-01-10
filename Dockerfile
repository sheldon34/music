#FROM eclipse-temurin:21-jre-alpine
#WORKDIR /app
##
### Copy the jar built locally (adjust the path/pattern as needed)
#COPY build/libs/*.jar app.jar
##
#EXPOSE 8080
##
#ENTRYPOINT ["java", "-jar", "app.jar"]
# STAGE 1: Build the application
FROM gradle:8.5-jdk21-alpine AS builder
WORKDIR /app

# Copy only necessary files for dependency resolution first (caching layer)
COPY build.gradle settings.gradle ./
COPY src ./src

# Build the application (skipping tests to speed up deployment)
# This creates the JAR file inside the Docker container
RUN gradle build -x test --no-daemon

# STAGE 2: Run the application
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copy the JAR file from the "builder" stage above
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]