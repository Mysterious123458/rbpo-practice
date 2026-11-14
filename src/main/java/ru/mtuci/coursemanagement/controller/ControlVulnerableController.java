package ru.mtuci.coursemanagement.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// КОНТРОЛЬНЫЙ пример для проверки порога SAST (шаг 4, практическая работа 02):
// намеренная SQL-конкатенация с известным срабатыванием правила. Ветка НЕ сливается в main.
@RestController
public class ControlVulnerableController {

    private final JdbcTemplate jdbc;

    public ControlVulnerableController(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @GetMapping("/api/control/sql")
    public String control(@RequestParam("value") String value) {
        String sql = "SELECT title FROM courses WHERE description LIKE '%" + value + "%'";
        return String.valueOf(jdbc.queryForList(sql));
    }
}
