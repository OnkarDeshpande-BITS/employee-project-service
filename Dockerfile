# Use an official OpenJDK runtime as a parent image
FROM openjdk:8-jre-alpine

LABEL Name="EmployeeProjectService" Version="1.0"
# set shell to bash
# source: https://stackoverflow.com/a/40944512/3128926
RUN apk update && apk add bash

# Set the working directory to /app
WORKDIR /app/lib

# Copy the fat jar into the container at /app
COPY /build/docker /app
ENV CLASSPATH=/app/lib

# Make port 8080 available to the world outside this container
EXPOSE 34004

# Run jar file when the container launches
CMD ["java", "-jar", "employee-project-service.jar"]