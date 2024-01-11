FROM amazoncorretto:11-alpine

WORKDIR /opt/app

COPY target/backend-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081

CMD ["java", "-jar", "app.jar"]