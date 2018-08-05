FROM java:8
VOLUME /tmp
ADD discovery-service-1.0.jar application.jar
RUN bash -c "touch /application.jar"
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "/application.jar"]
