FROM openjdk:8

WORKDIR /app
ADD build/libs/jalgoarena-events-*.jar /app/

ENV BOOTSTRAP_SERVERS=kafka1:9092,kafka2:9093,kafka3:9094
EXPOSE 5005

CMD java -Dserver.port=5005 -jar /app/jalgoarena-events-*.jar