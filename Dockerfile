FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
#
## Copy the jar built locally (adjust the path/pattern as needed)
RUN gradle build -x test --no-daemon#
EXPOSE 8080
#
ENTRYPOINT ["java", "-jar", "app.jar"]