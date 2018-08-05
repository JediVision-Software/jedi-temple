FROM java:8
VOLUME /tmp
ADD user-service-1.0.jar application.jar
RUN bash -c "touch /application.jar"
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/application.jar"]
