FROM openjdk:8-jre-alpine

MAINTAINER Jacek Spolnik <jacek.spolnik@gmail.com>

WORKDIR /app
COPY build/libs/jalgoarena-events-*.jar /app/

EXPOSE 5005
CMD ["/usr/bin/java", "-jar", "/app/jalgoarena-events-*.jar"]