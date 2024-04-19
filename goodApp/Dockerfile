# Use an existing Maven Docker image as a base
# Use an existing base image
FROM ubuntu:20.04

# Install the Google Cloud SDK
RUN apt-get update && apt-get install -y curl gnupg
RUN echo "deb [signed-by=/usr/share/keyrings/cloud.google.gpg] https://packages.cloud.google.com/apt cloud-sdk main" | tee -a /etc/apt/sources.list.d/google-cloud-sdk.list
RUN curl https://packages.cloud.google.com/apt/doc/apt-key.gpg | apt-key --keyring /usr/share/keyrings/cloud.google.gpg add -
RUN apt-get update && apt-get install -y google-cloud-sdk
RUN  gcloud auth application-default login || true
FROM maven:3.8.1-openjdk-11-slim AS build
FROM openjdk:11-jre-slim
FROM maven:3.8.1-openjdk-11-slim AS b2
# Set the working directory inside the container
WORKDIR /app

# Copy the project's pom.xml to the container
COPY pom.xml .


# Copy the entire project source code into the container
COPY src ./src

# Build the application
RUN mvn clean install

# Use a lightweight base image for the application runtime
# Command to run the application when the container starts
CMD ["bash", "-c", "mvn exec:java || true"]
