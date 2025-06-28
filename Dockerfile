FROM openjdk:17

# Update packages to reduce vulnerabilities and remove cache
RUN apt-get update && \
	apt-get upgrade -y && \
	apt-get autoremove -y && \
	apt-get clean && \
	rm -rf /var/lib/apt/lists/*

WORKDIR /app
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
