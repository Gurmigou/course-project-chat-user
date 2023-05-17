FROM openjdk:17-alpine3.14
COPY target/*.jar /user.jar
ENTRYPOINT ["java","-jar","/user.jar"]