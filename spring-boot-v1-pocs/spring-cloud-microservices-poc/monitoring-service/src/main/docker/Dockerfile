FROM java:8
VOLUME /tmp
ADD monitoring-service-1.0.jar application.jar
RUN bash -c "touch /application.jar"
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/application.jar"]
