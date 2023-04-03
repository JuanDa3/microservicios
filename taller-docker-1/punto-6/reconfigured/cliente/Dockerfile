FROM maven:3.6-jdk-11 as build

WORKDIR /app

ADD pom.xml .
RUN mvn package -Dmaven.test.skip -Declipselink.weave.skip

ADD src src
RUN mvn package -DskipTests



FROM openjdk:11-jre-slim
WORKDIR /app

COPY --from=build /app/target/cliente-1.0-SNAPSHOT.jar ./

CMD ["java", "-jar", "cliente.jar"]