# download maven image from Docker Hub
FROM maven:3.5.2-jdk-8-alpine AS MAVEN_BUILD
 
# author who maintains this file
MAINTAINER posadskiy.com
 
# copy and packing source code in docker image
COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn package
 
# download openjdk image
FROM openjdk:8-jre-alpine
 
# run war file in docker container
WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/*.war /app/app.war
ENTRYPOINT ["java", "-jar", "app.war"]
