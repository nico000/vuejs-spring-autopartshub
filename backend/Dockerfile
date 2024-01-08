FROM amazoncorretto:21-alpine

# [LABEL]
LABEL maintenair="Laniau Nicolas"
LABEL description="Autoparts service"

# [ Environnement variables]
ENV SERVER_PORT=8081

# [Argument]
ARG JAR_FILE=target/backend-*.jar

# [User]
# créer un groupe user et met user dans le groupe user
RUN addgroup --system user \
    && adduser --system --ingroup user user

USER user

# [Action]
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar

# [Ports]
EXPOSE ${SERVER_PORT}

# Entry point
# se base sur ce fichier pour les valeurs random de réseaux éviter de pas "-Djava.security.egd=file:/dev/./urandom"
ENTRYPOINT ["java" , "-jar" , "-Djava.security.egd=file:/dev/./urandom" , "app.jar"]
