FROM amazoncorretto:11-alpine

WORKDIR /opt/app

COPY target/backend-*.jar app.jar

EXPOSE 8081

CMD ["java", "-jar", "app.jar"]