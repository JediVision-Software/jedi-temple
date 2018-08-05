FROM java:8
VOLUME /tmp
ADD gateway-service-1.0.jar application.jar
RUN bash -c "touch /application.jar"
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "/application.jar"]
