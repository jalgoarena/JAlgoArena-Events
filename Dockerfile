FROM openjdk:8

MAINTAINER Jacek Spolnik <jacek.spolnik@gmail.com>

WORKDIR /app
ADD build/libs/jalgoarena-events-*.jar /app/

EXPOSE 5005
CMD java -jar /app/jalgoarena-events-*.jar