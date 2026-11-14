# КОНТРОЛЬНЫЙ Dockerfile (шаг 4 работы 04): намеренно плохой, ветка НЕ сливается.
FROM eclipse-temurin:latest
WORKDIR /app
COPY . .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/target/course-management-0.0.1-SNAPSHOT.jar"]
