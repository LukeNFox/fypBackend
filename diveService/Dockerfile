FROM openjdk:8-jdk-alpine
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
COPY scubaSOS.apk /usr/appstore/
CMD ["java", "-Xmx200m", "-jar", "/app.jar"]
