FROM java:8
ADD demo-0.0.1-SNAPSHOT.jar app.jar
ADD /music.tar.gz  /
EXPOSE 8080
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-jar","/app.jar"]

