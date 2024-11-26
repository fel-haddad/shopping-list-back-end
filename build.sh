#!/bin/bash

# Update package list and install OpenJDK 17
sudo apt-get update
sudo apt-get install -y openjdk-17-jdk

# Set JAVA_HOME environment variable
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk
export PATH=$JAVA_HOME/bin:$PATH

# Run Maven build
./mvnw clean install

# Run the application (adjust as necessary for your project)
java -jar target/your-app.jar
