# course-management

Учебное приложение для практических работ по курсу «Разработка безопасного программного обеспечения» (МТУСИ, gitflic.mtuci.ru).

- **Автор:** Тимошенко Владислав Сергеевич
- **Группа:** БКС2401
- **Номер студенческого билета:** 24074
- **Приложение:** CRUD студентов и курсов, Spring Boot 3.5.4, Java 21, H2 in-memory, Thymeleaf

## Локальная сборка и запуск

Требования: JDK 21 (проверено на Temurin 21.0.9), Maven 3.9.9 либо Maven Wrapper.

```bash
mvn clean verify      # сборка с тестами, без пропусков
mvn spring-boot:run   # запуск приложения
```

После запуска: http://localhost:8080 — интерфейс, /students, /courses — страницы,
/h2-console — консоль БД (JDBC URL: jdbc:h2:mem:courseapp, пользователь sa, пароль пустой).

## Конвейер (практическая работа 01)

Конфигурация: gitflic-ci.yaml. Этапы: build → test → package.

| Задача | Что делает | Артефакты |
| --- | --- | --- |
| build | mvn clean compile | — |
| test | mvn test | отчёты surefire (junit), при падении тоже |
| package | mvn package | JAR + jar.sha256 (SHA256 артефакта) |

Агент группы с тегом `barkov`, образ `maven:3.9.9-eclipse-temurin-21` (pull_policy: if-not-present),
кэш локального репозитория Maven — только для ускорения.

## Версии инструментов

- JDK: Temurin 21.0.9 (локально; в CI — eclipse-temurin-21 внутри образа Maven)
- Maven: 3.9.9 (в CI), Maven Wrapper 3.9.9 (локально)
- Spring Boot: 3.5.4
