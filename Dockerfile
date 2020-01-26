FROM openjdk:11.0.5-jre-slim

EXPOSE 8085

ADD /target/training-matrix-0.0.1-SNAPSHOT.jar training-matrix-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","training-matrix-0.0.1-SNAPSHOT.jar"]