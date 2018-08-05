FROM java:8
VOLUME /tmp
ADD task-service-1.0.jar application.jar
RUN bash -c "touch /application.jar"
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/application.jar"]
