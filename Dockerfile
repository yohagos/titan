FROM openjdk:20-jdk

ARG JAR_FILE=target/*.jar
COPY target/titanApp.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]

