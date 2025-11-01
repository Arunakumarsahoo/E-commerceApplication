FROM openjdk:17-oracle

ENV SPRING_PROFILE_ACTIVE prod

COPY target/user-service-0.0.1-SNAPSHOT.jar user-service.jar

ENTRYPOINT [ "java", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "-jar", "user-service.jar" ]