#!/bin/bash

# Install OpenJDK 17 without sudo (for environments that support apt-get)
apt-get update
apt-get install -y openjdk-17-jdk

# Set the JAVA_HOME environment variable for the current session
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk
export PATH=$JAVA_HOME/bin:$PATH

# Verify Java installation
java -version

# Proceed with your build (Maven example)
./mvnw clean install
