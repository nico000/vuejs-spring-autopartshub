FROM amazoncorretto:21-alpine

# [LABEL]
LABEL maintainer="Laniau Nicolas"
LABEL description="Autoparts service"

# [ Environnement variables]
ENV SERVER_PORT=8081

# [Argument]
ARG JAR_FILE=target/backend-0.0.1-SNAPSHOT.jar

# [User]
RUN addgroup --system user \
    && adduser --system --ingroup user user

USER user

# [Action]
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar

# [Ports]
EXPOSE ${SERVER_PORT}

# Entry point
ENTRYPOINT ["java", "-jar", "-Djava.security.egd=file:/dev/./urandom", "app.jar"]