FROM anapsix/alpine-java:8
# VOLUME /tmp
#ARG JAR_FILE
ADD target/config-service-0.0.1-SNAPSHOT.jar /app.jar
RUN mkdir -p /app/config
#RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
