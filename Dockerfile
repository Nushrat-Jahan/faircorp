# Base Java image
FROM openjdk:11

# H2 database port, username, password and path
ENV \
    APP_PORT="8080" \
    H2_USER="sa" \
    H2_PASSWORD="" \
    H2_DATABASE_STORED="jdbc:h2:mem:faircorp"

ARG JAR_FILE=build/libs/*SNAPSHOT.jar
# Startup script
COPY ${JAR_FILE} app.jar
# Port to expose
EXPOSE $APP_PORT
# Set entrypoint
ENTRYPOINT ["java","-jar","/app.jar"]