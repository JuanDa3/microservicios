FROM maven:3.8.3-openjdk-17 as build

COPY "./out/artifacts/socket_jar/socket.jar" "app.jar"

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]



