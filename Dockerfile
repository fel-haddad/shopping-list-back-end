# Step 1: Use an official OpenJDK 17 image from Docker Hub
FROM openjdk:17-jdk-slim

# Step 2: Set the working directory inside the container
WORKDIR /app

# Step 3: Copy the project files into the container
COPY . /app

# Step 4: Make sure Maven is used to build the application
RUN ./mvnw clean install

# Expose the port your app listens on
EXPOSE 1234

# Step 5: Set the default command to run the application (replace with actual jar location)
CMD ["java", "-jar", "target/demo-0.0.1-SNAPSHOT.jar"]
