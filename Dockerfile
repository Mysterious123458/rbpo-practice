# Практическая работа 04: образ приложения для проверенного коммита.
# Базовый образ зафиксирован по версии (JRE, не JDK); пользователь без root.
FROM eclipse-temurin:21.0.9_11-jre-alpine

RUN addgroup -S app && adduser -S app -G app

WORKDIR /app

COPY target/course-management-0.0.1-SNAPSHOT.jar app.jar

# Метаданные исходного коммита попадают внутрь образа
ARG COMMIT_SHA=unknown
ENV APP_COMMIT_SHA=$COMMIT_SHA

USER app

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
