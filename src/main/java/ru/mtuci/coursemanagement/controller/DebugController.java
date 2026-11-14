package ru.mtuci.coursemanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DebugController {

    // КОНТРОЛЬНЫЙ дефект для проверки блокировки DAST (практическая работа 05, шаг 5).
    // Ветка НЕ сливается в main и удаляется после контрольного запуска.
    @GetMapping("/api/debug/crash")
    public String crash() {
        throw new RuntimeException("Контрольная необработанная ошибка 500 для проверки блокировки DAST");
    }
}
