FROM java:8
VOLUME /tmp
ADD configuration-service-1.0.jar application.jar
RUN bash -c "touch /application.jar"
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "/application.jar"]
