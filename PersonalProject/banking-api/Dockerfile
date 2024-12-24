# Use a lightweight OpenJDK image
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven wrapper and POM file for dependency resolution
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Run Maven to download dependencies without building the full application
RUN chmod +x mvnw && ./mvnw dependency:resolve

# Copy the source code to the container
COPY src ./src

# Build the application
RUN ./mvnw clean install -DskipTests

# Copy the built JAR file to the container
COPY target/banking-api-0.0.1-SNAPSHOT.jar /app/banking-api.jar

# Expose the application's port
EXPOSE 8080

# Define the command to run the application
ENTRYPOINT ["java", "-jar", "/app/banking-api.jar"]
