package ru.mtuci.coursemanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.mtuci.coursemanagement.repository.CourseRepository;
import ru.mtuci.coursemanagement.repository.StudentRepository;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AdminController {

    private final StudentRepository students;
    private final CourseRepository courses;

    @Value("${app.admin.token:}")
    private String adminToken;

    // Практическая работа 05: тестовый защищённый маршрут для DAST (заголовок X-Admin-Token).
    // Токен передаётся только через переменные окружения CI/CD, в репозитории его нет.
    @GetMapping("/api/admin/stats")
    public ResponseEntity<Map<String, Object>> stats(
            @RequestHeader(value = "X-Admin-Token", required = false) String token) {
        if (adminToken.isBlank() || !adminToken.equals(token)) {
            return ResponseEntity.status(401).build();
        }
        Map<String, Object> stats = new LinkedHashMap<>();
        stats.put("students", students.count());
        stats.put("courses", courses.count());
        return ResponseEntity.ok(stats);
    }
}
