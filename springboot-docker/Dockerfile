FROM openjdk:17-oracle

ENV SPRING_PROFILE_ACTIVE prod

COPY target/springboot-docker-0.0.1-SNAPSHOT.jar springboot-docker.jar

ENTRYPOINT [ "java","-Dspring.profiles.active=${SPRING_PROFILE_ACTIVE}",  "-jar", "springboot-docker.jar" ]