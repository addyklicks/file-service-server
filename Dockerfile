FROM eclipse-temurin:17-jdk-alpine as builder

# Set working directory
WORKDIR /app

# Copy Gradle files and source code
COPY gradle gradle
COPY build.gradle.kts settings.gradle.kts gradlew ./
COPY src src

# Build the application
RUN ./gradlew bootJar

# Use a minimal runtime image
FROM eclipse-temurin:17-jre-alpine

# Set working directory in runtime container
WORKDIR /app

# Copy the built jar file from the builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
