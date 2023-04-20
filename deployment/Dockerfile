FROM adoptopenjdk/openjdk11-openj9:alpine-slim
VOLUME /tmp

ARG DEPENDENCY=applications/app-service/build
COPY ${DEPENDENCY}/libs /app
COPY ${DEPENDENCY}/*.jar /app

ENV JAVA_OPTS=" -Xshareclasses:name=cacheapp,cacheDir=/cache,nonfatal -XX:+UseContainerSupport -XX:MaxRAMPercentage=70 -Djava.security.egd=file:/dev/./urandom"

USER root
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS  -jar app/PichinchaBank.jar" ]