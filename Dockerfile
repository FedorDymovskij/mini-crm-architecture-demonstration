FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY target/*-SNAPSHOT.jar app.jar

CMD [ "java", "-jar", "app.jar" ]
